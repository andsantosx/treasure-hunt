package br.edu.satc.treasurehunt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.satc.treasurehunt.data.datasource.LocalHintDataSource
import br.edu.satc.treasurehunt.data.repository.HintRepositoryImpl
import br.edu.satc.treasurehunt.domain.usecase.GetHintsUseCase
import br.edu.satc.treasurehunt.domain.usecase.ValidateAnswerUseCase
import br.edu.satc.treasurehunt.presentation.TreasureHuntScreen
import br.edu.satc.treasurehunt.presentation.TreasureHuntViewModel
import br.edu.satc.treasurehunt.ui.theme.TreasureHuntTheme

class MainActivity : ComponentActivity() {

    // Simple manual DI for the purpose of the exercise
    private val viewModel: TreasureHuntViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val dataSource = LocalHintDataSource()
                val repository = HintRepositoryImpl(dataSource)
                val getHintsUseCase = GetHintsUseCase(repository)
                val validateAnswerUseCase = ValidateAnswerUseCase()
                return TreasureHuntViewModel(getHintsUseCase, validateAnswerUseCase) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TreasureHuntTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TreasureHuntScreen(viewModel = viewModel)
                }
            }
        }
    }
}
