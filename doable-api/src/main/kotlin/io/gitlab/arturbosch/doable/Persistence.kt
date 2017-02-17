package io.gitlab.arturbosch.doable

/**
 * @author Artur Bosch
 */

interface Persistence {
	fun save(memory: Memory)
	fun load(memoryName: String): Memory
}