package com.marta.agent

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MartaAgentApplication

fun main(args: Array<String>) {
	runApplication<MartaAgentApplication>(*args)
}
