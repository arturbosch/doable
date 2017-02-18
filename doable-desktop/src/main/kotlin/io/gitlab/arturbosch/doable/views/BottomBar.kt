package io.gitlab.arturbosch.doable.views

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXTextField
import io.gitlab.arturbosch.doable.ApproveEvent
import io.gitlab.arturbosch.doable.ResetEvent
import io.gitlab.arturbosch.doable.append
import io.gitlab.arturbosch.doable.bus
import io.gitlab.arturbosch.doable.data.TaskController
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

	private val taskController: TaskController by inject()

	private val addButton = JFXButton("Add")
	private val descriptionField = JFXTextField()

	override val root: Parent = vbox {
		hbox {
			margin = Insets(10.0, 0.0, 20.0, 0.0)
			alignment = Pos.BOTTOM_CENTER
			append(descriptionField) {
				prefWidth = 300.0
				promptText = "Enter task"
			}
			append(addButton) {
				setOnMouseClicked { addNewTask() }
			}
		}
		hbox {
			alignment = Pos.CENTER
			append(JFXButton("Approve")) {
				prefWidth = 200.0
				setOnMouseClicked { bus.fire(ApproveEvent) }
			}
			append(JFXButton("Reset")) {
				prefWidth = 200.0
				setOnMouseClicked { bus.fire(ResetEvent) }
			}

		}
	}

	private fun addNewTask() {
		val description = descriptionField.text
		if (description.isNullOrBlank().not()) {
			taskController.addTask(description)
			descriptionField.text = ""
		}
	}

}