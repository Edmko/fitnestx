package ua.edmko.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.concurrent.atomic.AtomicBoolean

abstract class BaseViewModel<S : ViewState, E : Event>(initialState: S) : ViewModel() {

    private var initialized = AtomicBoolean(false)

    open fun initialize() {
        if (!initialized.compareAndSet(false, true)) {
            return
        }
    }

    private val _viewStates: MutableStateFlow<S?> = MutableStateFlow(initialState)
    val viewStates: StateFlow<S?> = _viewStates.asStateFlow()

    protected var viewState: S
        get() = _viewStates.value
            ?: throw UninitializedPropertyAccessException("\"viewState\" was queried before being initialized")
        set(value) {
            /** StateFlow doesn't work with same values */
            if (_viewStates.value == value) {
                _viewStates.value = null
            }
            _viewStates.value = value
        }

    abstract fun obtainEvent(viewEvent: E)
}
