package sahak.sahakyan.dynamicdeals.presentation.ui.sign_in

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sahak.sahakyan.dynamicdeals.R
import sahak.sahakyan.dynamicdeals.data.model.User
import sahak.sahakyan.dynamicdeals.navigation.Screen.Home
import sahak.sahakyan.dynamicdeals.presentation.ui.components.ButtonStyle
import sahak.sahakyan.dynamicdeals.presentation.ui.components.CustomOutlinedTextField
import sahak.sahakyan.dynamicdeals.presentation.ui.components.CustomPasswordOutlinedTextField
import sahak.sahakyan.dynamicdeals.presentation.ui.components.CustomText
import sahak.sahakyan.dynamicdeals.presentation.viewmodel.AuthViewModel
import sahak.sahakyan.dynamicdeals.presentation.viewmodel.SignUpViewModel
import sahak.sahakyan.dynamicdeals.utils.NAV_GRAPH
import sahak.sahakyan.dynamicdeals.utils.Resource
import sahak.sahakyan.dynamicdeals.utils.SIGN_IN_SCREEN
import sahak.sahakyan.dynamicdeals.utils.SIGN_UP_SCREEN

@Composable
fun SignInScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToSignUp: () -> Unit = {},
    navigateToHome: () -> Unit = {}
) {

    val authState by viewModel.authState.observeAsState()
    val userState = viewModel.userState.observeAsState()
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val context = LocalContext.current

    val user = remember {
        MutableLiveData<User?>(userState.value?.user ?: User())
    }

    var email by remember {
        mutableStateOf<String>(user.value?.email ?: "")
    }
    var password by remember {
        mutableStateOf<String>(user.value?.password ?: "")
    }

    var passwordVisibility by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 153.dp, start = 33.dp, end = 33.dp, bottom = 161.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(34.dp)
            ) {
                Text(
                    text = "Welcome Back",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.SansSerif,
                    )
                )
                Text(
                    text = "Discover Exclusive Deals",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.SansSerif,
                    )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(31.dp)
            ) {
                // Inputs
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(25.dp)
                ) {
                    CustomOutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        placeHolder = "Email",
                        textFieldModifier = Modifier
                            .height(45.dp)
                            .fillMaxWidth(),
                    )
                    CustomPasswordOutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeHolder = "Password",
                        textFieldModifier = Modifier
                            .height(45.dp)
                            .fillMaxWidth(),
                        isPassword = true,
                        passwordVisibility = passwordVisibility,
                        onPasswordVisibilityChange = { passwordVisibility = it }
                    )
                }

                // Text
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navigateToSignUp()
                        },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomText(
                        text = "Don’t have an account yet ?",
                        fontSize = 12.sp,
                    )
                    Spacer(modifier = Modifier.width(32.dp))
                    CustomText(
                        text = "Sign Up",
                        fontSize = 12.sp,
                    )
                }

                // Button
                ButtonStyle(
                    text = "Sign In",
                    fontSize = 13.sp,
                    shape = 20,
                    containerColor = colorResource(id = R.color.yellow),
                    modifier = Modifier
                        .width(107.dp)
                        .height(30.dp),
                ) {
                    // TODO: Navigation to Home Screen
                    user.value = User(
                        email = email,
                        password = password,
                    )

                    val check = checkInputs(email,password,)
                    if (check == "0") {
                        lifecycleScope.launch {
                            val setUser = launch(Dispatchers.Main) {
                                viewModel.setUser(user.value!!)
                            }
                            setUser.join()
                            Log.i(
                                SIGN_IN_SCREEN,
                                "On Sign In Button Click: user = ${viewModel.userState.value?.user}"
                            )
                            navigateToHome()
                        }
                    } else {
                        Toast.makeText(context, check, Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }

    }

    when (authState) {
        is Resource.Loading -> {
            Log.i(SIGN_IN_SCREEN, "Loading:")
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
        is Resource.Success -> {
            Log.i(SIGN_IN_SCREEN, "Success")
        }

        is Resource.Error -> {
            Log.i(SIGN_IN_SCREEN, "Error:")
            Toast.makeText(context, "Not correct Email or Password", Toast.LENGTH_SHORT).show()
        }

        null -> {
            Log.i(SIGN_IN_SCREEN, "null:")
        }
    }


}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen()
}

private fun checkInputs(
    email: String,
    password: String,
): String {
    if (email.isBlank()) return "Email cannot be empty"
    if (password.isBlank()) return "Password cannot be empty"
    return "0"
}