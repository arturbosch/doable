package io.gitlab.arturbosch.doable.data

import io.gitlab.arturbosch.doable.Persistence
import tornadofx.ViewModel
import tornadofx.find

/**
 * @author Artur Bosch
 */
class MemoryModel(val database: Persistence = find<TextDatabase>()) : ViewModel() {

	private val memory = database.load("Test").toObservable()
	var currentList = memory.lists[0]

	fun tasks(): MutableList<ObservableTask> = currentList.tasks
	fun addTask(observableTask: ObservableTask) = currentList.tasks.add(observableTask)

}

