package io.gitlab.arturbosch.doable

import io.gitlab.arturbosch.doable.views.BottomBar
import io.gitlab.arturbosch.doable.views.ListContainer
import io.gitlab.arturbosch.doable.views.TopBar
import javafx.application.Application
import tornadofx.App
import tornadofx.View
import tornadofx.borderpane
import tornadofx.opcr
import tornadofx.vbox
import kotlin.reflect.KClass

/**
 * @author Artur Bosch
 */
class DoableApp : App() {
	override val primaryView: KClass<out View> = UI::class
}

fun main(vararg args: String) {
	Application.launch(DoableApp::class.java, *args)
}

class AccelTest : View() {
	override val root = vbox {
		opcr(this, find<BottomBar>().root)
	}
}

class UI : View("Doable") {

	override val root = borderpane {
		prefWidth = 400.0
		prefHeight = 500.0
		top = find<TopBar>().root
		center = find<ListContainer>().root
		bottom = find<BottomBar>().root
	}
}
