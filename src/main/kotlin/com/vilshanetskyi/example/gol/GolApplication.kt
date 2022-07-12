package com.vilshanetskyi.example.gol

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class GolApplication

fun main(args: Array<String>) {
	runApplication<GolApplication>(*args)
}
