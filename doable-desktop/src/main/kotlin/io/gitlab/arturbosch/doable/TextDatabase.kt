package io.gitlab.arturbosch.doable

import com.google.gson.Gson
import java.io.File

/**
 * @author Artur Bosch
 */
class TextDatabase(val file: File) : Persistence {

	private val parser = Gson()

	override fun save(memory: Memory) {
		val json = parser.toJson(memory)
		file.writeText(json)
	}

	override fun load(memoryName: String): Memory {
		return parser.fromJson(file.readText(), Memory::class.java)
	}

}