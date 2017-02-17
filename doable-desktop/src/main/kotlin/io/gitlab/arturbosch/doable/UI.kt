package io.gitlab.arturbosch.doable

import com.jfoenix.controls.JFXListView
import javafx.application.Application
import javafx.collections.FXCollections
import tornadofx.App
import tornadofx.View
import tornadofx.borderpane
import tornadofx.bottom
import tornadofx.button
import tornadofx.center
import tornadofx.choicebox
import tornadofx.hbox
import tornadofx.label
import tornadofx.opcr
import tornadofx.top
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
	override val root = borderpane {

		top {
			hbox {
				label("Stars")
				choicebox(values = FXCollections.observableList(listOf("Test", "Test2"))) {

				}
			}
		}

		center {
			opcr(this, JFXListView<Task>()) {
			}
		}

		bottom {
			hbox {
				button("Approve")
				button("Reset")
			}
		}
	}
}

