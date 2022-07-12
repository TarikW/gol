package com.vilshanetskyi.example.gol.board

class Field(
    val rows: List<List<Cell>>
) {
    init {
        setFieldIntoCells()
    }

    private fun setFieldIntoCells() {
        rows.forEach { cells ->
            cells.forEach {
                it.field = this
            }
        }
    }
}