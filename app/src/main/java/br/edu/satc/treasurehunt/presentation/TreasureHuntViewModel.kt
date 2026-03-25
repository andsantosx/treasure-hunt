package br.edu.satc.treasurehunt.presentation

import androidx.lifecycle.ViewModel
import br.edu.satc.treasurehunt.domain.usecase.GetHintsUseCase
import br.edu.satc.treasurehunt.domain.usecase.ValidateAnswerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TreasureHuntViewModel(
    private val getHintsUseCase: GetHintsUseCase,
    private val validateAnswerUseCase: ValidateAnswerUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(TreasureHuntState())
    val state: StateFlow<TreasureHuntState> = _state.asStateFlow()

    init {
        _state.update { it.copy(hints = getHintsUseCase()) }
    }

    fun onStartGame() {
        _state.update { 
            it.copy(
                startTime = System.currentTimeMillis(),
                currentHintIndex = 0,
                userAnswer = "",
                showError = false,
                isGameFinished = false
            ) 
        }
    }

    fun onAnswerChange(answer: String) {
        _state.update { it.copy(userAnswer = answer, showError = false) }
    }

    fun onNextHint(onNavigate: (Int, Boolean) -> Unit) {
        val currentState = _state.value
        val hint = currentState.currentHint ?: return

        if (validateAnswerUseCase(currentState.userAnswer, hint.answer)) {
            if (currentState.currentHintIndex < currentState.hints.size - 1) {
                val nextIndex = currentState.currentHintIndex + 1
                _state.update { 
                    it.copy(
                        currentHintIndex = nextIndex,
                        userAnswer = "",
                        showError = false
                    ) 
                }
                onNavigate(nextIndex, false)
            } else {
                val totalTime = System.currentTimeMillis() - currentState.startTime
                _state.update { 
                    it.copy(
                        totalTime = totalTime,
                        isGameFinished = true
                    ) 
                }
                onNavigate(0, true)
            }
        } else {
            _state.update { it.copy(showError = true) }
        }
    }

    fun onBack() {
        _state.update { 
            if (it.currentHintIndex > 0) {
                it.copy(currentHintIndex = it.currentHintIndex - 1, userAnswer = "", showError = false)
            } else {
                it
            }
        }
    }

    fun onRestart() {
        _state.update { TreasureHuntState(hints = getHintsUseCase()) }
    }
}
