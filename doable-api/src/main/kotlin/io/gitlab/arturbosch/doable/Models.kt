package io.gitlab.arturbosch.doable

import java.util.Date

/**
 * @author Artur Bosch
 */

data class Task(var description: String, var done: Boolean = false)

data class WorkingList(var name: String, var tasks: MutableList<Task>)

data class Achievement(var workingListName: String, var date: Date, var points: Int)

data class Streak(var days: Int)

data class Memory(var name: String, var lists: MutableList<WorkingList>, var achievements: MutableList<Achievement>,
				  var date: Date, var points: Int, var currentStreak: Streak)