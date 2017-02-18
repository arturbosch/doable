package io.gitlab.arturbosch.doable.views

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXTextField
import io.gitlab.arturbosch.doable.append
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Parent
import tornadofx.View
import tornadofx.hbox
import tornadofx.margin
import tornadofx.vbox

/**
 * @author Artur Bosch
 */
class BottomBar : View() {
	override val root: Parent = vbox {
		hbox {
			margin = Insets(10.0, 0.0, 20.0, 0.0)
			alignment = Pos.BOTTOM_CENTER
			append(JFXTextField()) {
				prefWidth = 300.0
				promptText = "Enter task"
			}
			append(JFXButton("Add"))
		}
		hbox {
			alignment = Pos.CENTER
			append(JFXButton("Approve")) {
				prefWidth = 200.0
			}
			append(JFXButton("Reset")) {
				prefWidth = 200.0
			}

		}
	}

}