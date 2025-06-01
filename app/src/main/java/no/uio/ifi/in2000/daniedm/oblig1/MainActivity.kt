package no.uio.ifi.in2000.daniedm.oblig1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import no.uio.ifi.in2000.daniedm.oblig1.ui.palindrome.PalindromeScreen
import no.uio.ifi.in2000.daniedm.oblig1.ui.theme.Daniedm_oblig1Theme
import no.uio.ifi.in2000.daniedm.oblig1.ui.unitconverter.UnitConverterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Daniedm_oblig1Theme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "ScreenPalindrome"
    ) {
        composable("ScreenPalindrome") {PalindromeScreen(navController)}
        composable("ScreenUnitConverter") {UnitConverterScreen(navController)  }
    }
}