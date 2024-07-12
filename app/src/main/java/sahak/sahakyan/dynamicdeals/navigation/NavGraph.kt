package sahak.sahakyan.dynamicdeals.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import sahak.sahakyan.dynamicdeals.navigation.Screen.*
import sahak.sahakyan.dynamicdeals.presentation.ui.home.HomeScreen
import sahak.sahakyan.dynamicdeals.presentation.ui.sign_in.SignInScreen
import sahak.sahakyan.dynamicdeals.presentation.ui.sign_up.SignUpScreen
import sahak.sahakyan.dynamicdeals.presentation.ui.verification.VerificationScreen
import sahak.sahakyan.dynamicdeals.presentation.viewmodel.AuthViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalLifecycleOwner
import sahak.sahakyan.dynamicdeals.utils.NAV_GRAPH

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun CustomNavGraph(
    navController: NavHostController
) {

    val authViewModel: AuthViewModel = hiltViewModel()
    val userState by authViewModel.userState.observeAsState()
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope


    NavHost(navController = navController, startDestination = SignUp.route) {
        composable(Home.route) {
            HomeScreen()
        }
        composable(SignIn.route) {
            SignInScreen(
                viewModel = authViewModel,
                navigateToSignUp = {
                    navController.navigate(SignUp.route)
                    authViewModel.setDefaultUser()
                },
                navigateToHome = {

                    val coroutine = lifecycleScope.launch(Dispatchers.IO){
                        authViewModel.signIn(userState!!.user.email,userState!!.user.password)
                    }
                    navController.navigate(Home.route)
                    authViewModel.setDefaultUser()
                }
            )
        }
        composable(SignUp.route) {
            SignUpScreen(
                viewModel = authViewModel,
                navigateToSignIn = {
                    navController.navigate(SignIn.route)
                    authViewModel.setDefaultUser()
                },
                navigateToVerification = {
                    val verification = lifecycleScope.launch {
                        Log.d(NAV_GRAPH, "navigateToVerification(): val verification = lifecycleScope.launch {} called with user = ${authViewModel.userState.value?.user.toString()}")
                        authViewModel.signUp(userState!!.user.email,userState!!.user.password)
                        Log.d(NAV_GRAPH, "navigateToVerification(): val verification = lifecycleScope.launch {} ended successfully")
                    }

                    lifecycleScope.launch {
                        Log.d(NAV_GRAPH, "navigateToVerification(): lifecycleScope.launch {} is started")
                        verification.join()
                        Log.d(NAV_GRAPH, "navigateToVerification(): lifecycleScope.launch {} navigating to VerifyEmail.route with auth state = ${authViewModel.authState.value.toString()}")
                        navController.navigate(VerifyEmail.route)
                        withContext(Dispatchers.Main) {
                            Log.d(NAV_GRAPH, "navigateToVerification(): lifecycleScope.launch {} setting default user in authViewModel")
                            authViewModel.setDefaultUser()
                        }
                        Log.d(NAV_GRAPH, "navigateToVerification(): lifecycleScope.launch {} ended successfully")
                    }
                }

            )
        }
        composable(VerifyEmail.route) {
            VerificationScreen(

            )
        }
    }

    LaunchedEffect(userState) {

    }

}