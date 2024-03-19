package br.com.fiap.screennavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.navArgument
import br.com.fiap.screennavigation.screens.LoginScreen
import br.com.fiap.screennavigation.screens.MenuScreen
import br.com.fiap.screennavigation.screens.PedidosScreen
import br.com.fiap.screennavigation.screens.PerfilScreen
import br.com.fiap.screennavigation.ui.theme.ScreenNavigationTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScreenNavigationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberAnimatedNavController()
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = "login",
                    ) {
                        composable(route = "login") {
                            LoginScreen(navController = navController)
                        }
                        composable(route = "menu") {
                            MenuScreen(navController = navController)
                        }
                        composable(
                            route = "pedidos",
                            arguments = listOf(navArgument("numero") { defaultValue = "Sem cliente" })
                        ) { backStackEntry ->
                            val numero = backStackEntry.arguments?.getString("numero")
                            PedidosScreen(navController = navController, numero ?: "")
                        }
                        composable(route = "perfil/{nome}") {
                            val nome: String? =
                                it.arguments?.getString("nome", "")
                            PerfilScreen(navController = navController, nome!!)
                        }
                    }
                }
            }
        }
    }
}


