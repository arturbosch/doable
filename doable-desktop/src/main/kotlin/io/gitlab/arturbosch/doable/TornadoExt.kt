package io.gitlab.arturbosch.doable

import javafx.event.EventTarget
import javafx.scene.Node
import tornadofx.addChildIfPossible

/**
 * @author Artur Bosch
 */

fun <T : Node> EventTarget.append(node: T, op: (T.() -> Unit)? = null): T {
	this.addChildIfPossible(node)
	op?.invoke(node)
	return node
}