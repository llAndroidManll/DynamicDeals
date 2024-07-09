package sahak.sahakyan.dynamicdeals.navigation

sealed class Screen(val route: String) {
    data object SignUp : Screen("sign_up")
    data object VerifyEmail : Screen("verify_email")
    data object SignIn : Screen("sign_in")
    data object ForgotPassword : Screen("forgot_password")
    data object Home : Screen("home")
}