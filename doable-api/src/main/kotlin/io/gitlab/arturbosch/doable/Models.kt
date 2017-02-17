package io.gitlab.arturbosch.doable

import java.util.Date

/**
 * @author Artur Bosch
 */

data class Task(val description: String, val done: Boolean)

data class WorkingList(val name: String, val tasks: MutableList<Task>)

data class Achievement(val workingListName: String, val date: Date, val points: Int)

data class Streak(val days: Int)

data class Memory(val name: String, val lists: MutableList<WorkingList>, val achievements: MutableList<Achievement>,
				  val day: Date, val points: Int, val currentStreak: Streak)