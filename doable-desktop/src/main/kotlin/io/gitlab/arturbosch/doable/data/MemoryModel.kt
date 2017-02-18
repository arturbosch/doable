package io.gitlab.arturbosch.doable.data

import io.gitlab.arturbosch.doable.Persistence
import io.gitlab.arturbosch.doable.SaveEvent
import io.gitlab.arturbosch.doable.bus
import tornadofx.DefaultScope
import tornadofx.ViewModel
import tornadofx.find

/**
 * @author Artur Bosch
 */
class MemoryModel(val database: Persistence = find<TextDatabase>()) : ViewModel() {

	init {
		bus.subscribe(SaveEvent::class.java, DefaultScope) {
			println("Its working!")
			database.save(observableMemory.memory)
		}
	}

	val observableMemory = database.load("Test").toObservable()
	var currentList = observableMemory.lists[0]

	fun tasks(): MutableList<ObservableTask> = currentList.tasks
	fun addTask(observableTask: ObservableTask) {
		currentList.tasks.add(observableTask)
		bus.fire(SaveEvent)
	}

	fun plusStar(amount: Int) {
		observableMemory.points += amount
	}
}

