package br.edu.satc.treasurehunt.presentation

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.satc.treasurehunt.presentation.components.GlassCard
import br.edu.satc.treasurehunt.presentation.components.MainBackground
import br.edu.satc.treasurehunt.presentation.components.PremiumButton
import java.util.Locale

@Composable
fun TreasureHuntScreen(viewModel: TreasureHuntViewModel) {
    val state by viewModel.state.collectAsState()
    val navController = rememberNavController()

    MainBackground {
        NavHost(
            navController = navController,
            startDestination = "home",
            enterTransition = { slideInHorizontally { it } + fadeIn() },
            exitTransition = { slideOutHorizontally { -it } + fadeOut() },
            popEnterTransition = { slideInHorizontally { -it } + fadeIn() },
            popExitTransition = { slideOutHorizontally { it } + fadeOut() }
        ) {
            composable("home") {
                HomeScreen(onStart = {
                    viewModel.onStartGame()
                    navController.navigate("hint/0")
                })
            }

            composable("hint/{index}") { backStackEntry ->
                val index = backStackEntry.arguments?.getString("index")?.toIntOrNull() ?: 0
                HintScreen(
                    state = state,
                    index = index,
                    onAnswerChange = viewModel::onAnswerChange,
                    onNext = {
                        viewModel.onNextHint { nextIndex, isFinished ->
                            if (isFinished) {
                                navController.navigate("treasure") {
                                    popUpTo("home") { inclusive = false }
                                }
                            } else {
                                navController.navigate("hint/$nextIndex")
                            }
                        }
                    },
                    onBack = {
                        if (navController.previousBackStackEntry != null) {
                            viewModel.onBack()
                            navController.popBackStack()
                        }
                    }
                )
            }

            composable("treasure") {
                TreasureScreen(
                    totalTimeMillis = state.totalTime,
                    onRestart = {
                        viewModel.onRestart()
                        navController.navigate("home") {
                            popUpTo("home") { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun HomeScreen(onStart: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "CAÇA AO\nTESOURO",
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Você está pronto para o desafio?",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.height(48.dp))
        // Requirement: botão "iniciar caça ao tesouro"
        PremiumButton(text = "Iniciar Caça ao Tesouro", onClick = onStart)
    }
}

@Composable
fun HintScreen(
    state: TreasureHuntState,
    index: Int,
    onAnswerChange: (String) -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    val hint = if (index < state.hints.size) state.hints[index] else null
    if (hint == null) return

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        
        Text(
            text = "Pista ${index + 1} de ${state.hints.size}",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(24.dp))

        GlassCard {
            Text(
                text = hint.question,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            OutlinedTextField(
                value = state.userAnswer,
                onValueChange = onAnswerChange,
                label = { Text("Sua resposta") },
                modifier = Modifier.fillMaxWidth(),
                isError = state.showError,
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true
            )
            
            AnimatedVisibility(visible = state.showError) {
                Text(
                    text = "Hmm, não é isso. Tente novamente!",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Requirement: botão "Voltar"
            TextButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Voltar")
            }
            
            // Requirement: botão "Próxima Pista"
            PremiumButton(
                text = "Próxima Pista",
                onClick = onNext,
                modifier = Modifier.width(180.dp)
            )
        }
    }
}

@Composable
fun TreasureScreen(totalTimeMillis: Long, onRestart: () -> Unit) {
    val seconds = (totalTimeMillis / 1000) % 60
    val minutes = (totalTimeMillis / (1000 * 60)) % 60
    val timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("✨🏆✨", fontSize = 80.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "PARABÉNS!",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary
        )
        // Requirement: "Parabéns! Você encontrou o tesouro!"
        Text(
            text = "Parabéns! Você encontrou o tesouro!",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        
        GlassCard {
            Text(
                text = "Tempo de Busca",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
            Text(
                text = timeFormatted,
                style = MaterialTheme.typography.displayLarge.copy(fontSize = 60.sp),
                color = MaterialTheme.colorScheme.secondary
            )
        }
        
        Spacer(modifier = Modifier.height(48.dp))
        
        PremiumButton(text = "Recomeçar", onClick = onRestart)
    }
}
