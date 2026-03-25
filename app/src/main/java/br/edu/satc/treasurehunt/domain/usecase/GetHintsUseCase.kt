package br.edu.satc.treasurehunt.domain.usecase

import br.edu.satc.treasurehunt.domain.model.Hint
import br.edu.satc.treasurehunt.domain.repository.HintRepository

class GetHintsUseCase(private val repository: HintRepository) {
    operator fun invoke(): List<Hint> {
        return repository.getHints()
    }
}
