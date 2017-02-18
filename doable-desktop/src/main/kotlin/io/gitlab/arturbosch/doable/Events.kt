package io.gitlab.arturbosch.doable

import tornadofx.EventBus
import tornadofx.FXEvent

/**
 * @author Artur Bosch
 */
object SaveEvent : FXEvent(EventBus.RunOn.BackgroundThread)

val bus = EventBus()