package io.gitlab.arturbosch.doable

import javafx.application.Application
import tornadofx.App
import tornadofx.View
import kotlin.reflect.KClass

/**
 * @author Artur Bosch
 */
class DoableApp : App() {
	override val primaryView: KClass<out View>
		get() = UI::class
}

fun main(vararg args: String) {
	Application.launch(DoableApp::class.java, *args)
}

class UI : View("Doable") {
	override val root: javafx.scene.Parent
		get() = throw UnsupportedOperationException()
}