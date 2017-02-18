package io.gitlab.arturbosch.doable.data

import com.google.gson.Gson
import io.gitlab.arturbosch.doable.Memory
import io.gitlab.arturbosch.doable.Persistence

/**
 * @author Artur Bosch
 */
class TextDatabase(val dir: HomeFolder) : Persistence {

	private val parser = Gson()

	override fun save(memory: Memory) {
		val json = parser.toJson(memory)
		val saveFile = dir.resolve(memory.name).toFile()
		saveFile.writeText(json)
	}

	override fun load(memoryName: String): Memory {
		val fromFile = dir.resolve(memoryName).toFile()
		return parser.fromJson(fromFile.readText(), Memory::class.java)
	}

}