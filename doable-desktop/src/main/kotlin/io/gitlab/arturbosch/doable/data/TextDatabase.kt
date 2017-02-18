package io.gitlab.arturbosch.doable.data

import com.google.gson.Gson
import io.gitlab.arturbosch.doable.Memory
import io.gitlab.arturbosch.doable.Persistence
import io.gitlab.arturbosch.doable.Streak
import io.gitlab.arturbosch.doable.WorkingList
import tornadofx.Controller
import tornadofx.find
import java.util.Date

/**
 * @author Artur Bosch
 */
class TextDatabase(val dir: HomeFolder = find<DoableFolder>()) : Controller(), Persistence {

	private val parser = Gson()

	override fun save(memory: Memory) {
		val json = parser.toJson(memory)
		val saveFile = dir.resolve(memory.name).toFile()
		saveFile.writeText(json)
	}

	override fun load(memoryName: String): Memory {
		val fromFile = dir.resolve(memoryName).toFile()
		if (!fromFile.exists()) {
			val memory = Memory("Test", mutableListOf(WorkingList("Test", mutableListOf())), mutableListOf(), Date(), 0, Streak(0))
			save(memory)
		}
		return parser.fromJson(fromFile.readText(), Memory::class.java)
	}

}