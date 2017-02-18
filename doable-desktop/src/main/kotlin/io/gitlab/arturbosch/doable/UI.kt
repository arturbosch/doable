package io.gitlab.arturbosch.doable

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXComboBox
import com.jfoenix.controls.JFXListView
import com.jfoenix.controls.JFXTextField
import javafx.application.Application
import javafx.collections.FXCollections
import javafx.event.EventTarget
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.control.ListCell
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import tornadofx.App
import tornadofx.View
import tornadofx.addChildIfPossible
import tornadofx.borderpane
import tornadofx.bottom
import tornadofx.center
import tornadofx.gridpane
import tornadofx.hbox
import tornadofx.label
import tornadofx.margin
import tornadofx.top
import tornadofx.vbox
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
		prefWidth = 400.0
		prefHeight = 500.0
		top {
			gridpane {
				columnConstraints.add(ColumnConstraints().apply { percentWidth = 50.0 })
				columnConstraints.add(ColumnConstraints().apply { percentWidth = 50.0 })
				add(HBox().apply {
					alignment = Pos.CENTER_LEFT
					label("Stars: ") {
						padding = Insets(5.0)
					}
					label("0") {
						padding = Insets(5.0)
					}
				}, 0, 0)
				add(HBox().apply {
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
				}, 1, 0)

			}
		}

		center {
			vbox {
				append(JFXListView<String>()) {
					VBox.setVgrow(this, Priority.ALWAYS)
					items = FXCollections.observableList(listOf("Hello", "World", "Chris", "Sex", "Mehr Sex", "Oh my Sex"))
				}
			}
		}

		bottom {
			vbox {
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
	}
}

fun <T : Node> EventTarget.append(node: T, op: (T.() -> Unit)? = null): T {
	this.addChildIfPossible(node)
	op?.invoke(node)
	return node
}