package ru.practicum.android.diploma.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A utility class designed to manage and execute actions with a specified delay,
 * effectively debouncing them.
 *
 * This class allows you to schedule an action to be performed after a certain period (`delayMillis`).
 * If a new action is scheduled using the `invoke` method before the previous one has executed,
 * the previous action is cancelled, and the timer is reset for the new action.
 * This ensures that the action is only performed once after a period of inactivity.
 *
 * The debounced actions are launched within the provided `coroutineScope`.
 * The `cancel` method can be used to explicitly cancel any pending debounced action.
 *
 * @property delayMillis The delay in milliseconds before the action is invoked.
 * @property coroutineScope The CoroutineScope in which the debounced action will be launched.
 */
class DebounceUtil (
    private var delayMillis: Long,
    private val coroutineScope: CoroutineScope
) {
    private var debounceJob: Job? = null

    /**
     * Invokes the given action after a specified delay, cancelling any previously scheduled actions.
     *
     * @param T The type of the result of the action.
     * @param action The suspend function to be executed after the delay.
     */
    fun <T> invoke(action: suspend () -> T) {
        debounceJob?.cancel()
        debounceJob = coroutineScope.launch {
            delay(delayMillis)
            action()
        }
    }

    /**
     * Explicitly cancels current debounce job if it's active.
     */
    fun cancel() {
        debounceJob?.cancel()
        debounceJob = null
    }
}
