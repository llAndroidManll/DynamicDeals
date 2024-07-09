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
import sahak.sahakyan.dynamicdeals.data.model.User
import sahak.sahakyan.dynamicdeals.presentation.viewmodel.SignUpViewModel
import sahak.sahakyan.dynamicdeals.utils.NAV_GRAPH

@Composable
fun CustomNavGraph(
    navController: NavHostController
) {

    val authViewModel: AuthViewModel = hiltViewModel()
    val userState by authViewModel.userState.observeAsState()

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
                    authViewModel.signIn(userState!!.user.email,userState!!.user.password)
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
                    Log.d(NAV_GRAPH, "CustomNavGraph-navigateToVerification -> (): ")
                    authViewModel.signUp(userState!!.user.email,userState!!.user.password)
                    navController.navigate(VerifyEmail.route)
                    authViewModel.setDefaultUser()
                }

            )
        }
        composable(VerifyEmail.route) {
            VerificationScreen()
        }
    }

    LaunchedEffect(userState) {

    }

}