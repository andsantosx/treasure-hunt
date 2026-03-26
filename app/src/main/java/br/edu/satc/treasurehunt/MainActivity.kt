package br.edu.satc.treasurehunt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import br.edu.satc.treasurehunt.presentation.TreasureHuntScreen
import br.edu.satc.treasurehunt.presentation.TreasureHuntViewModel
import br.edu.satc.treasurehunt.presentation.TreasureHuntViewModelFactory
import br.edu.satc.treasurehunt.ui.theme.TreasureHuntTheme

class MainActivity : ComponentActivity() {
    private val viewModel: TreasureHuntViewModel by viewModels {
        TreasureHuntViewModelFactory()
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
