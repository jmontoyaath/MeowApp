package com.es.jma.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<State, Action>(initialState: State) : ViewModel() {

    val uiState: StateFlow<State> get() = state.asStateFlow()
    val action get() = _action.receiveAsFlow()

    protected var state: MutableStateFlow<State> = MutableStateFlow(initialState)
    private val _action = Channel<Action?>(Channel.BUFFERED)

    protected fun Action.send() {
        _action.trySendBlocking(this)
    }

    protected fun updateState(block: (State) -> State) {
        state.update(block)
    }
}