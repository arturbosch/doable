package io.gitlab.arturbosch.doable.data

import io.gitlab.arturbosch.doable.Achievement
import io.gitlab.arturbosch.doable.Memory
import io.gitlab.arturbosch.doable.Streak
import io.gitlab.arturbosch.doable.Task
import io.gitlab.arturbosch.doable.WorkingList
import java.util.Date
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * @author Artur Bosch
 */

class ObservableTask(task: Task) {
	var description: String by Delegates.observable(task.description) { kProperty: KProperty<*>, old: String, new: String ->
		task.description = new
	}
	var done: Boolean by Delegates.observable(task.done) { kProperty: KProperty<*>, old: Boolean, new: Boolean ->
		task.done = new
	}
}

class ObservableWorkingList(workingList: WorkingList) {
	var name: String by Delegates.observable(workingList.name) { kProperty: KProperty<*>, old: String, new: String ->
		workingList.name = new
	}
	var tasks: MutableList<Task> by Delegates.observable(workingList.tasks) {
		kProperty: KProperty<*>, old: MutableList<Task>, new: MutableList<Task> ->
		workingList.tasks = new
	}
}

class ObservableAchievement(achievement: Achievement) {
	var workingListName: String by Delegates.observable(achievement.workingListName) { kProperty: KProperty<*>, old: String, new: String ->
		achievement.workingListName = new
	}
	var points: Int by Delegates.observable(achievement.points) { kProperty: KProperty<*>, old: Int, new: Int -> achievement.points = new }
	var date: Date by Delegates.observable(achievement.date) { kProperty: KProperty<*>, old: Date, new: Date -> achievement.date = new }
}

class ObservableStreak(streak: Streak) {
	var days: Int by Delegates.observable(streak.days) { kProperty: KProperty<*>, old: Int, new: Int -> streak.days = new }
}

class ObservableMemory(memory: Memory) {
	var name: String by Delegates.observable(memory.name) { kProperty: KProperty<*>, old: String, new: String ->
		memory.name = new
	}
	var points: Int by Delegates.observable(memory.points) { kProperty: KProperty<*>, old: Int, new: Int -> memory.points = new }
	var date: Date by Delegates.observable(memory.date) { kProperty: KProperty<*>, old: Date, new: Date -> memory.date = new }
	var lists: MutableList<WorkingList> by Delegates.observable(memory.lists) {
		kProperty: KProperty<*>, old: MutableList<WorkingList>, new: MutableList<WorkingList> ->
		memory.lists = new
	}
	var achievements: MutableList<Achievement> by Delegates.observable(memory.achievements) {
		kProperty: KProperty<*>, old: MutableList<Achievement>, new: MutableList<Achievement> ->
		memory.achievements = new
	}
	var currentStreak: Streak by Delegates.observable(memory.currentStreak) { kProperty: KProperty<*>, old: Streak, new: Streak ->
		memory.currentStreak = new
	}
}