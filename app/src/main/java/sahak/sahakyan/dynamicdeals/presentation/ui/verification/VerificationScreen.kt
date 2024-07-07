package sahak.sahakyan.dynamicdeals.presentation.ui.verification

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.MutableLiveData
import sahak.sahakyan.dynamicdeals.R
import sahak.sahakyan.dynamicdeals.data.model.User
import sahak.sahakyan.dynamicdeals.presentation.ui.components.ButtonStyle
import sahak.sahakyan.dynamicdeals.presentation.ui.components.CustomOutlinedTextField
import sahak.sahakyan.dynamicdeals.presentation.ui.components.CustomText
import sahak.sahakyan.dynamicdeals.presentation.viewmodel.SignUpViewModel

@Composable
fun VerificationScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    sendCodeAgain : ()->Unit = {},
    navigateToHome : ()->Unit = {}
) {

    val user = remember {
        MutableLiveData<User>(viewModel.user.value)
    }

    var email by remember {
        mutableStateOf<String>(user.value?.email ?: "")
    }
    var code by remember {
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
                verticalArrangement = Arrangement.spacedBy(57.dp)
            ) {
                Text(
                    text = "Verify Its You",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.SansSerif,
                    )
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(13.dp)
                ) {
                    Text(
                        text = "We sent verification code to",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.SansSerif,
                        )
                    )
                    Text(
                        text = email.ifEmpty { "example@gmail.com" },
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.SansSerif,
                            textDecoration = TextDecoration.Underline,
                        )
                    )
                    Text(
                        text = "Please check your inbox and\nenter the code below.",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.SansSerif,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(31.dp)
            ) {
                // Inputs
                CustomOutlinedTextField(
                    value = code,
                    onValueChange = {
                        code = it
                    },
                    placeHolder = "Code",
                    textFieldModifier = Modifier
                        .height(45.dp)
                        .fillMaxWidth()
                    ,
                )

                // Text
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            sendCodeAgain()
                        },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomText(
                        text = "Send Again ?",
                        fontSize = 12.sp,
                    )
                    Spacer(modifier = Modifier.width(32.dp))
                    CustomText(
                        text = "Send ",
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
                    // TODO: Navigation to Home Screen
                    navigateToHome()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VerificationScreenPreview() {
    VerificationScreen()
}
