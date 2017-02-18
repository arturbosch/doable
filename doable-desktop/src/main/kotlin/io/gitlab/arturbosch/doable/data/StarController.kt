package io.gitlab.arturbosch.doable.data

import tornadofx.Controller

/**
 * @author Artur Bosch
 */
class StarController : Controller() {

	private val memoryModel by inject<MemoryModel>()

	fun stars(): Int = memoryModel.observableMemory.points
	fun addStar() = memoryModel.plusStar(1)
}