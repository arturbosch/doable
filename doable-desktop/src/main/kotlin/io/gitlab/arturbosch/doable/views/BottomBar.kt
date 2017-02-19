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
import javafx.scene.control.Button
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import tornadofx.View
import tornadofx.hbox
import tornadofx.margin
import tornadofx.vbox

/**
 * @author Artur Bosch
 */
class BottomBar : View() {

	private val taskController: TaskController by inject()

	private val addButton = Button("Add")
	private val descriptionField = JFXTextField()

	override val root: Parent = vbox {
		hbox {
			margin = Insets(10.0, 0.0, 20.0, 0.0)
			alignment = Pos.BOTTOM_CENTER
			append(descriptionField) {
				prefWidth = 300.0
				promptText = "Enter task"
				accelerators.put(KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_ANY)) { this.requestFocus() }
				setOnKeyPressed { if (it.code == KeyCode.ENTER) addNewTask() }
			}
			append(addButton) {
				setOnAction { addNewTask() }
			}
		}
		hbox {
			alignment = Pos.CENTER
			append(JFXButton("Approve")) {
				prefWidth = 200.0
				accelerator(KeyCodeCombination(KeyCode.A, KeyCombination.SHORTCUT_ANY, KeyCombination.SHIFT_ANY))
				setOnAction { bus.fire(ApproveEvent) }
			}
			append(JFXButton("Reset")) {
				prefWidth = 200.0
				accelerator(KeyCodeCombination(KeyCode.R, KeyCombination.SHORTCUT_ANY, KeyCombination.SHIFT_ANY))
				setOnAction { bus.fire(ResetEvent) }
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