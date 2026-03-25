package br.edu.satc.treasurehunt.domain.usecase

class ValidateAnswerUseCase {
    operator fun invoke(userAnswer: String, correctAnswer: String): Boolean {
        return userAnswer.trim().equals(correctAnswer, ignoreCase = true)
    }
}
