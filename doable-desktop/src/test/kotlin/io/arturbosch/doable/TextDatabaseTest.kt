package io.arturbosch.doable

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import io.gitlab.arturbosch.doable.Memory
import io.gitlab.arturbosch.doable.Streak
import io.gitlab.arturbosch.doable.Task
import io.gitlab.arturbosch.doable.WorkingList
import io.gitlab.arturbosch.doable.data.HomeFolder
import io.gitlab.arturbosch.doable.data.TextDatabase
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path
import java.util.Date

/**
 * @author Artur Bosch
 */
class TextDatabaseTest {

	object TestFolder : HomeFolder {
		val tempDir: Path = Files.createTempDirectory("doable")
		override fun resolve(subPath: String): Path = tempDir.resolve(subPath)
	}

	@Test
	fun saveLoadWorks() {
		val database = TextDatabase(TestFolder)
		val workingList = WorkingList("Everyday", mutableListOf(Task("DoIt", true)))
		val memory = Memory("Test", mutableListOf(workingList), mutableListOf(), Date(), 0, Streak(0))

		database.save(memory)
		val loaded = database.load("Test")

		assertThat(loaded.name, equalTo("Test"))
		assertThat(loaded.lists.size, equalTo(1))
		assertThat(loaded.lists[0].tasks.size, equalTo(1))
	}
}