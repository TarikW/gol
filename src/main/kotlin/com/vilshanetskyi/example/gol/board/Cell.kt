package com.vilshanetskyi.example.gol.board

class Cell(
    val state: State
) {
    lateinit var field: Field

    fun getNeighborsCount(state: State): Int {
        var neighborsCount = 0
        val rowIndex = field.rows.indexOfFirst { it.contains(this) }
        val row = field.rows[rowIndex]
        val columnIndex = row.indexOf(this)

        if (rowIndex > 0 && field.rows[rowIndex - 1][columnIndex].state == state) {
            neighborsCount++
        }

        if (rowIndex < field.rows.size - 1 && field.rows[rowIndex + 1][columnIndex].state == state) {
            neighborsCount++
        }

        if (columnIndex > 0 && row[columnIndex - 1].state == state) {
            neighborsCount++
        }

        if (columnIndex < row.size - 1 && row[columnIndex + 1].state == state) {
            neighborsCount++
        }

        return neighborsCount
    }

    enum class State {
        LIVE, DEAD
    }
}