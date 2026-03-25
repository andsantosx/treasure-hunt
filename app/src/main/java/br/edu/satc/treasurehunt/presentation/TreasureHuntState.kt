package br.edu.satc.treasurehunt.presentation

import br.edu.satc.treasurehunt.domain.model.Hint

data class TreasureHuntState(
    val hints: List<Hint> = emptyList(),
    val currentHintIndex: Int = 0,
    val userAnswer: String = "",
    val showError: Boolean = false,
    val startTime: Long = 0L,
    val totalTime: Long = 0L,
    val isGameFinished: Boolean = false
) {
    val currentHint: Hint? get() = if (hints.isNotEmpty() && currentHintIndex < hints.size) hints[currentHintIndex] else null
}
