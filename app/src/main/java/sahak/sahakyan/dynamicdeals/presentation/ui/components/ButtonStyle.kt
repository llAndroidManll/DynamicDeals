package sahak.sahakyan.dynamicdeals.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonStyle(
    modifier: Modifier = Modifier,
    text: String = "",
    textColor: Color = Color.White,
    fontSize: TextUnit = 18.sp,
    borderStroke: BorderStroke? = null,
    shape: Int = 0,
    contentColor : Color = Color.White,
    containerColor : Color = Color.Transparent,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(shape),
        border = borderStroke,
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor,
            containerColor = containerColor
        ),
    ) {
        CustomText(
            text = text,
            color = textColor,
            fontSize = fontSize,
        )
    }
}