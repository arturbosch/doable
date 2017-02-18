package io.gitlab.arturbosch.doable.views

import com.jfoenix.controls.JFXCheckBox
import com.jfoenix.controls.JFXListView
import io.gitlab.arturbosch.doable.SaveEvent
import io.gitlab.arturbosch.doable.append
import io.gitlab.arturbosch.doable.bus
import io.gitlab.arturbosch.doable.data.ObservableTask
import io.gitlab.arturbosch.doable.data.ObservableWorkingList
import io.gitlab.arturbosch.doable.data.TaskController
import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import tornadofx.View
import tornadofx.gridpane
import tornadofx.vbox

/**
 * @author Artur Bosch
 */
class ListContainer : View() {

	private val taskController: TaskController by inject()

	private var observableWorkingList: ObservableWorkingList = taskController.currentList()

	override val root: Parent = vbox {
		append(JFXListView<ObservableTask>()) {
			VBox.setVgrow(this, Priority.ALWAYS)
			cellCache { task ->
				gridpane {
					columnConstraints.add(ColumnConstraints().apply { percentWidth = 20.0 })
					columnConstraints.add(ColumnConstraints().apply { percentWidth = 80.0 })
					addColumn(0, JFXCheckBox().apply {
						isSelected = task.done
						selectedProperty().addListener { observableValue, old, new ->
							task.done = new
							bus.fire(SaveEvent)
						}
					})
					addColumn(1, Label().apply {
						text = task.description
						textProperty().addListener { observableValue, old, new ->
							task.description = new
							bus.fire(SaveEvent)
						}
					})
				}
			}
			itemsProperty().bindBidirectional(observableWorkingList.observableTasks)
		}
	}
}
