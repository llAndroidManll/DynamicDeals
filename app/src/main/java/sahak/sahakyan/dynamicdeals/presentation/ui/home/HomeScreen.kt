package sahak.sahakyan.dynamicdeals.presentation.ui.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sahak.sahakyan.dynamicdeals.R

@Composable
fun HomeScreen() {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
        ,
        topBar = {
            TopAppBarSearchField()
        },
        bottomBar = {
            BottomAppBar()
        },
        snackbarHost = {},
//        floatingActionButtonPosition = ,
//        containerColor =,
//        contentColor =,
//        contentWindowInsets =,
        content = { padding ->
            Text(text = "", modifier = Modifier.padding(padding))
        }

    )

}

@Composable
fun BottomAppBar() {
    //TODO: Create a Navigation Bar, Hello ChatGPT
}

@Composable
fun TopAppBarSearchField(
    onMenuClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 30.dp,
                start = 16.dp,
                end = 16.dp
            )
            .border(1.dp, Color.Black)
            .height(50.dp)

        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Menu
        Column(
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
                .shadow(
                    elevation = 10.dp,
                    spotColor = Color.White,
                    shape = RoundedCornerShape(100)
                )
                .clickable {
                    onMenuClick()
                }
            ,
            verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,
            
        ) {
            Icon(painter = painterResource(R.drawable.menu), contentDescription = "")
        }

        // Profile
        Column(
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
                .shadow(
                    elevation = 10.dp,
                    spotColor = Color.White,
                    shape = RoundedCornerShape(100)
                )
                .clickable {
                    onProfileClick()
                }
            ,
            verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Image(
                modifier = Modifier
                    .height(30.dp)
                    .width(30.dp)
                ,
                painter = painterResource(R.drawable.men),
                contentDescription = ""
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}