package io.gitlab.arturbosch.doable.data

import io.gitlab.arturbosch.doable.Task
import tornadofx.Controller

/**
 * @author Artur Bosch
 */
class TaskController : Controller() {

	private val memoryModel: MemoryModel by inject()

	fun currentList(): ObservableWorkingList = memoryModel.currentList

	fun loadTasks(): MutableList<ObservableTask> {
		return memoryModel.tasks()
	}

	fun addTask(description: String) = memoryModel.addTask(ObservableTask(Task(description)))

}