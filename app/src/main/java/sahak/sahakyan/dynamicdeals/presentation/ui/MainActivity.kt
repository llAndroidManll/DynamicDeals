package sahak.sahakyan.dynamicdeals.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import sahak.sahakyan.dynamicdeals.presentation.ui.sign_up.SignUpScreen
import sahak.sahakyan.dynamicdeals.presentation.ui.theme.DynamicDealsTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DynamicDealsTheme {
                SignUpScreen()
            }
        }
    }
}