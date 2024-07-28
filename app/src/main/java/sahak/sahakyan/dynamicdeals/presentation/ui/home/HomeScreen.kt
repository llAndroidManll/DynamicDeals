package sahak.sahakyan.dynamicdeals.presentation.ui.home

import android.view.RoundedCorner
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import sahak.sahakyan.dynamicdeals.R
import sahak.sahakyan.dynamicdeals.navigation.BottomNavItem
import sahak.sahakyan.dynamicdeals.presentation.ui.components.CustomText

@Composable
fun HomeScreen() {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.gray))
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
fun BottomAppBar(
    onIconClick: (String) -> Unit = {}
) {

    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf(BottomNavItem.Home, BottomNavItem.Search, BottomNavItem.Cart, BottomNavItem.Heart)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .shadow(
                elevation = 10.dp,
                spotColor = Color.White,
                shape = RoundedCornerShape(topStartPercent = 20, topEndPercent = 20)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStartPercent = 20, topEndPercent = 20)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->

                val isSelected = selectedItem == index

                if (isSelected) {
                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(5.dp),
                    ) {
                        IconButton(
                            onClick = {
                                onIconClick(item.title)
                            },
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                                .padding(1.dp)
                                .background(
                                    color = colorResource(id = R.color.yellow),
                                    shape = CircleShape
                                ),
                        ) {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.title,
                                tint = Color.White,
                            )
                        }
                        CustomText(
                            text = item.title,
                            fontSize = 10.sp,
                            color = Color.Black
                        )
                    }
                } else {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title,
                            tint = colorResource(id = R.color.gray)
                        )
                    }
                }

            }
        }
    }
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
                top = 50.dp,
                start = 16.dp,
                end = 16.dp
            )
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
            Icon(
                painter = painterResource(R.drawable.menu),
                contentDescription = "",
                tint = colorResource(id = R.color.yellow),
            )
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