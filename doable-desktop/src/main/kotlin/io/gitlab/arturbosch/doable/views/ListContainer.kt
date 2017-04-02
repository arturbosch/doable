package io.gitlab.arturbosch.doable.views

import com.jfoenix.controls.JFXCheckBox
import com.jfoenix.controls.JFXListView
import io.gitlab.arturbosch.doable.ApproveEvent
import io.gitlab.arturbosch.doable.ResetEvent
import io.gitlab.arturbosch.doable.SaveEvent
import io.gitlab.arturbosch.doable.StarEvent
import io.gitlab.arturbosch.doable.append
import io.gitlab.arturbosch.doable.data.ObservableTask
import io.gitlab.arturbosch.doable.data.ObservableWorkingList
import io.gitlab.arturbosch.doable.data.TaskController
import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import tornadofx.FX
import tornadofx.View
import tornadofx.gridpane
import tornadofx.vbox

/**
 * @author Artur Bosch
 */
class ListContainer : View() {

	init {
		subscribe<ApproveEvent> {
			val items = listView.items
			if (items.all { it.done }) {
				FX.eventbus.fire(StarEvent)
				items.forEach { it.done = false }
			}
		}
		subscribe<ResetEvent> {
			listView.items.forEach { it.done = false }
		}
	}

	private val taskController: TaskController by inject()

	private var observableWorkingList: ObservableWorkingList = taskController.currentList()
	private val listView = JFXListView<ObservableTask>()

	override val root: Parent = vbox {
		append(listView) {
			VBox.setVgrow(this, Priority.ALWAYS)
			cellCache { task ->
				gridpane {
					columnConstraints.add(ColumnConstraints().apply { percentWidth = 20.0 })
					columnConstraints.add(ColumnConstraints().apply { percentWidth = 80.0 })
					addColumn(0, JFXCheckBox().apply {
						selectedProperty().bindBidirectional(task.doneProperty)
						selectedProperty().addListener { observableValue, old, new ->
							task.done = new
							FX.eventbus.fire(SaveEvent)
						}
					})
					addColumn(1, Label().apply {
						textProperty().bindBidirectional(task.descriptionProperty)
						textProperty().addListener { observableValue, old, new ->
							task.description = new
							FX.eventbus.fire(SaveEvent)
						}
					})
				}
			}
			itemsProperty().bindBidirectional(observableWorkingList.observableTasks)
		}
	}
}
