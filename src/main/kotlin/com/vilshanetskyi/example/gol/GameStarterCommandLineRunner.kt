package com.vilshanetskyi.example.gol

import com.vilshanetskyi.example.gol.board.Cell
import com.vilshanetskyi.example.gol.board.Field
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class GameStarterCommandLineRunner(
    private val fieldCreator: FieldCreator,
) : CommandLineRunner {

    private lateinit var field: Field

    override fun run(vararg args: String?) {
        startGame()
    }

    private fun startGame() {
        printWelcomeMessage()

        if (playerWantContinue()) {
            createField()
            playGameUntilPlayerWantsContinue()
        }
    }

    private fun createField() {
        field = fieldCreator.createField()
    }

    private fun playGameUntilPlayerWantsContinue() {
        var generation = 1

        do {
            printGeneration(generation++)
            renderField()
            simulateNextMove()
        } while (playerWantContinue())
    }

    private fun renderField() {
        for (row in field.rows) {
            val stringRow = row.joinToString("") { if (it.state == Cell.State.LIVE) "#" else " " }
            println("| $stringRow |")
        }
    }

    private fun simulateNextMove() {
        val newCells = field.rows.flatten().map {
            Cell(getNewStateAccordingToRules(it))
        }.chunked(25)

        field = Field(newCells)
    }

    private fun printGeneration(generation: Int) {
        println("Generation $generation")
    }

    private fun getNewStateAccordingToRules(cell: Cell): Cell.State = when (cell.state) {
        Cell.State.LIVE -> {
            val liveNeighbors = cell.getNeighborsCount(Cell.State.LIVE)

            if (liveNeighbors < 2 || liveNeighbors > 3) {
                Cell.State.DEAD
            }
            else {
                cell.state
            }
        }
        Cell.State.DEAD -> {
            val l = cell.getNeighborsCount(Cell.State.LIVE)
            if (cell.getNeighborsCount(Cell.State.LIVE) == 3) {
                Cell.State.LIVE
            }
            else {
                cell.state
            }
        }
    }

    companion object {
        private fun printWelcomeMessage() {
            println("To Start/Continue press Enter, or \"q\" for exit")
        }

        private fun playerWantContinue() = "q" != readLine()
    }
}