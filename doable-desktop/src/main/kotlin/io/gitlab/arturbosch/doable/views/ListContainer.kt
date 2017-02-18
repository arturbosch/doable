package io.gitlab.arturbosch.doable.views

import com.jfoenix.controls.JFXListView
import io.gitlab.arturbosch.doable.append
import javafx.collections.FXCollections
import javafx.scene.Parent
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import tornadofx.View
import tornadofx.vbox

/**
 * @author Artur Bosch
 */
class ListContainer : View() {
	override val root: Parent = vbox {
		append(JFXListView<String>()) {
			VBox.setVgrow(this, Priority.ALWAYS)
			items = FXCollections.observableList(listOf("Hello", "World", "Chris", "Sex", "Mehr Sex", "Oh my Sex"))
		}
	}
}