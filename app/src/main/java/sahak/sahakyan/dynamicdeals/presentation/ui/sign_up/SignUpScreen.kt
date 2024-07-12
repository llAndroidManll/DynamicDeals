package sahak.sahakyan.dynamicdeals.presentation.ui.sign_up

import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sahak.sahakyan.dynamicdeals.R
import sahak.sahakyan.dynamicdeals.data.model.User
import sahak.sahakyan.dynamicdeals.presentation.ui.components.ButtonStyle
import sahak.sahakyan.dynamicdeals.presentation.ui.components.CustomOutlinedTextField
import sahak.sahakyan.dynamicdeals.presentation.ui.components.CustomText
import sahak.sahakyan.dynamicdeals.presentation.viewmodel.AuthViewModel
import sahak.sahakyan.dynamicdeals.utils.Resource
import sahak.sahakyan.dynamicdeals.utils.SIGN_UP_SCREEN

@Composable
fun SignUpScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToSignIn : ()->Unit = {},
    navigateToVerification : ()->Unit = {}
) {

    val authState = viewModel.authState.observeAsState()
    val userState = viewModel.userState.observeAsState()
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope

    val user = remember {
        MutableLiveData<User?>(userState.value?.user ?: User())
    }

    var email by remember {
        mutableStateOf<String>(user.value?.email ?: "")
    }
    var name by remember {
        mutableStateOf<String>(user.value?.name ?: "")
    }
    var password by remember {
        mutableStateOf<String>(user.value?.password ?: "")
    }
    var secondPassword by remember {
        mutableStateOf<String>("")
    }

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
                    text = "Welcome To Our Store",
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
                        .fillMaxWidth(),
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
                            .fillMaxWidth()
                        ,
                    )
                    CustomOutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        placeHolder = "Name",
                        textFieldModifier = Modifier
                            .height(45.dp)
                            .fillMaxWidth()
                        ,
                    )
                    CustomOutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        placeHolder = "Password",
                        textFieldModifier = Modifier
                            .height(45.dp)
                            .fillMaxWidth()
                        ,
                    )
                    CustomOutlinedTextField(
                        value = secondPassword,
                        onValueChange = {
                            secondPassword = it
                        },
                        placeHolder = "Confirm Password",
                        textFieldModifier = Modifier
                            .height(45.dp)
                            .fillMaxWidth()
                        ,
                    )
                }

                // Text
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            // TODO: Navigation to Sign In Screen
                            navigateToSignIn()
                        },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomText(
                        text = "Already have an account?",
                        fontSize = 12.sp,
                    )
                    Spacer(modifier = Modifier.width(32.dp))
                    CustomText(
                        text = "Sign In",
                        fontSize = 12.sp,
                    )
                }

                // Button
                ButtonStyle(
                    text = "Sign Up",
                    fontSize = 13.sp,
                    shape = 20,
                    containerColor = colorResource(id = R.color.yellow),
                    modifier = Modifier
                        .width(107.dp)
                        .height(30.dp)
                    ,
                ) {

                    val setUser = lifecycleScope.launch(Dispatchers.Main) {
                        user.value = User(
                            email = email,
                            name = name,
                            password = password,
                        )
                        viewModel.setUser(user = user.value!!)
                    }

                    val joinedCoroutine = lifecycleScope.launch(Dispatchers.IO) {
                        setUser.join()
                    }

                    lifecycleScope.launch {
                        joinedCoroutine.join()
                        navigateToVerification()
                    }
                }
            }
        }
    }

    authState.value.let {
        when (it) {
            is Resource.Loading -> CircularProgressIndicator()
            is Resource.Success -> {
                // TODO: Navigate to Verification Screen
                navigateToVerification()
            }
            is Resource.Error -> {
                // TODO: Show error message
            }
            null -> {

            }
        }
    }
}