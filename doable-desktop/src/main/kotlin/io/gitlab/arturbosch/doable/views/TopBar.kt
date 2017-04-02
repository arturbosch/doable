package io.gitlab.arturbosch.doable.views

import com.jfoenix.controls.JFXComboBox
import io.gitlab.arturbosch.doable.StarEvent
import io.gitlab.arturbosch.doable.append
import io.gitlab.arturbosch.doable.data.StarController
import javafx.collections.FXCollections
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.scene.control.ListCell
import javafx.scene.layout.ColumnConstraints
import tornadofx.View
import tornadofx.find
import tornadofx.gridpane
import tornadofx.hbox
import tornadofx.label

/**
 * @author Artur Bosch
 */
class TopBar : View() {
	override val root: Parent = gridpane {
		columnConstraints.add(ColumnConstraints().apply { percentWidth = 50.0 })
		columnConstraints.add(ColumnConstraints().apply { percentWidth = 50.0 })
		add(find<StarComponent>().root, 0, 0)
		add(find<WorkingListComponent>().root, 1, 0)
	}
}

class StarComponent(private val starController: StarController = find(StarController::class)) : View() {

	init {
		subscribe<StarEvent> {
			starController.addStar()
			loadStars()
		}
	}

	private val starsLabel = Label()
	private fun loadStars() {
		starsLabel.text = starController.stars().toString()
	}

	override val root: Parent = hbox {
		alignment = Pos.CENTER_LEFT
		label("Stars: ") {
			padding = Insets(5.0)
		}
		append(starsLabel) {
			loadStars()
			padding = Insets(5.0)
		}
	}
}

class WorkingListComponent : View() {

	override val root: Parent = hbox {
		alignment = Pos.CENTER_RIGHT
		append(JFXComboBox<String>(FXCollections.observableList(listOf("Test", "Test2")))) {
			selectionModel.select(0)
			buttonCell = object : ListCell<String>() {
				public override fun updateItem(item: String?, empty: Boolean) {
					super.updateItem(item, empty)
					if (item != null) {
						text = item
						alignment = Pos.CENTER_RIGHT
						val old = padding
						padding = Insets(old.top, 0.0, old.bottom, 0.0)
					}
				}
			}
			setCellFactory {
				object : ListCell<String>() {
					public override fun updateItem(item: String?, empty: Boolean) {
						super.updateItem(item, empty)
						if (item != null) {
							text = item
							alignment = Pos.CENTER_RIGHT
							padding = Insets(3.0, 3.0, 3.0, 0.0)
						}
					}
				}
			}
		}
	}
}
