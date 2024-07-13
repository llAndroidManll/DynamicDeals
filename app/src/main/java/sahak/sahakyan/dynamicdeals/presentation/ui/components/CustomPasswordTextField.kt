package sahak.sahakyan.dynamicdeals.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sahak.sahakyan.dynamicdeals.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPasswordOutlinedTextField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    value: String = "",
    onValueChange: (String) -> Unit = {},
    placeHolder: String = "",
    textStyle: TextStyle = TextStyle(
        color = Color(android.graphics.Color.parseColor("#C0C0C0")),
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.SansSerif,
    ),
    textFieldModifier: Modifier = Modifier
        .fillMaxWidth()
        .height(45.dp),
    spacerModifier: Modifier = Modifier
        .fillMaxWidth()
        .height(1.dp),
    containerColor: Color = Color.White,
    unfocusedBorderColor: Color = Color.Transparent,
    focusedBorderColor: Color = Color.Transparent,
    cursorColor: Color = Color.Transparent,
    isPassword: Boolean = false,
    passwordVisibility: Boolean = false,
    onPasswordVisibilityChange: (Boolean) -> Unit = {}
) {
    Column(
        modifier = modifier,
    ) {
        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            value = value,
            onValueChange = onValueChange,
            modifier = textFieldModifier,
            textStyle = textStyle,
            placeholder = {
                Text(
                    text = placeHolder,
                    style = textStyle
                )
            },
            visualTransformation = if (isPassword && !passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                if (isPassword) {
                    val icon = if (passwordVisibility)
                        painterResource(id = R.drawable.visibility)
                    else
                        painterResource(id = R.drawable.visibility_off)

                    IconButton(onClick = { onPasswordVisibilityChange(!passwordVisibility) }) {
                        Icon(painter = icon, contentDescription = "Toggle password visibility")
                    }
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = containerColor,
                unfocusedBorderColor = unfocusedBorderColor,
                focusedBorderColor = focusedBorderColor,
                cursorColor = cursorColor,
            ),
        )
        Spacer(
            modifier = spacerModifier
        )
    }
}