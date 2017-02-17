package io.arturbosch.doable

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import io.gitlab.arturbosch.doable.Memory
import io.gitlab.arturbosch.doable.Streak
import io.gitlab.arturbosch.doable.Task
import io.gitlab.arturbosch.doable.TextDatabase
import io.gitlab.arturbosch.doable.WorkingList
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Files
import java.util.Date

/**
 * @author Artur Bosch
 */
class TextDatabaseTest {

	@Test
	fun saveLoadWorks() {
		val tempDir = Files.createTempDirectory("doable")
		val file = File(tempDir.resolve("Test.doable").toString())
		val database = TextDatabase(file)
		val workingList = WorkingList("Everyday", mutableListOf(Task("DoIt", true)))
		val memory = Memory("Test", mutableListOf(workingList), mutableListOf(), Date(), 0, Streak(0))

		database.save(memory)
		val loaded = database.load("Test")

		assertThat(loaded.name, equalTo("Test"))
		assertThat(loaded.lists.size, equalTo(1))
		assertThat(loaded.lists[0].tasks.size, equalTo(1))
	}
}