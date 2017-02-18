package io.gitlab.arturbosch.doable.data

import tornadofx.Controller
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

/**
 * @author Artur Bosch
 */
interface HomeFolder {
	fun resolve(subPath: String): Path
}

class DoableFolder : Controller(), HomeFolder {

	fun checkDir(path: Path): Path {
		if (Files.notExists(path)) {
			Files.createDirectories(path)
		}
		return path
	}

	override fun resolve(subPath: String): Path {
		return home().resolve(subPath)
	}

	fun home(): Path {
		return checkDir(Paths.get(System.getProperty("user.home"), ".doable"))
	}
}