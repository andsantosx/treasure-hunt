package br.edu.satc.treasurehunt.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.satc.treasurehunt.data.datasource.LocalHintDataSource
import br.edu.satc.treasurehunt.data.repository.HintRepositoryImpl
import br.edu.satc.treasurehunt.domain.usecase.GetHintsUseCase
import br.edu.satc.treasurehunt.domain.usecase.ValidateAnswerUseCase

class TreasureHuntViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TreasureHuntViewModel::class.java)) {
            val dataSource = LocalHintDataSource()
            val repository = HintRepositoryImpl(dataSource)
            val getHintsUseCase = GetHintsUseCase(repository)
            val validateAnswerUseCase = ValidateAnswerUseCase()
            return TreasureHuntViewModel(getHintsUseCase, validateAnswerUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
