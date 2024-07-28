package sahak.sahakyan.dynamicdeals.presentation.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(
    text: String = "",
    fontSize: TextUnit = 24.sp,
    color: Color = Color.White,
    fontFamily: FontFamily = FontFamily.SansSerif
) {
    Text(
        text = text,
        style = TextStyle(
            color = color,
            fontSize = fontSize,
            fontWeight = FontWeight.Normal,
            fontFamily = fontFamily,
        )
    )
}