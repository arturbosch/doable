package io.gitlab.arturbosch.doable.views

import com.jfoenix.controls.JFXCheckBox
import com.jfoenix.controls.JFXListView
import io.gitlab.arturbosch.doable.append
import io.gitlab.arturbosch.doable.data.ObservableTask
import io.gitlab.arturbosch.doable.data.TaskController
import javafx.collections.FXCollections
import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.scene.control.ListCell
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

	private var tasks: MutableList<ObservableTask> = taskController.loadTasks()

	override val root: Parent = vbox {
		append(JFXListView<ObservableTask>()) {
			VBox.setVgrow(this, Priority.ALWAYS)
			items = FXCollections.observableList(tasks)
			setCellFactory {
				ListContainerViewCell()
			}
		}
	}
}

class ListContainerViewCell : ListCell<ObservableTask>() {

	private val doneBox = JFXCheckBox()
	private val description = Label()

	private val root: Parent = gridpane {
		columnConstraints.add(ColumnConstraints().apply { percentWidth = 20.0 })
		columnConstraints.add(ColumnConstraints().apply { percentWidth = 80.0 })
		addColumn(0, doneBox)
		addColumn(1, description)
	}

	override fun updateItem(item: ObservableTask?, empty: Boolean) {
		super.updateItem(item, empty)
		item?.let {
			doneBox.isSelected = it.done
			description.text = it.description
			this.graphic = root
			this.text = null
		}
	}
}