package sahak.sahakyan.dynamicdeals.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import sahak.sahakyan.dynamicdeals.navigation.CustomNavGraph
import sahak.sahakyan.dynamicdeals.presentation.ui.sign_up.SignUpScreen
import sahak.sahakyan.dynamicdeals.presentation.ui.theme.DynamicDealsTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        FirebaseApp.initializeApp(this)

        setContent {
            DynamicDealsTheme {
                val navController = rememberNavController()
                CustomNavGraph(navController = navController)
            }
        }
    }
}