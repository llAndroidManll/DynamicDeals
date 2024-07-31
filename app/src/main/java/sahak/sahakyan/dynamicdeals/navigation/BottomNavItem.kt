package sahak.sahakyan.dynamicdeals.navigation

import sahak.sahakyan.dynamicdeals.R

sealed class BottomNavItem(
    var title: String,
    var icon: Int
) {
    data object Home : BottomNavItem(
        "Home",
        R.drawable.home
    )

    data object Search : BottomNavItem(
        "Search",
        R.drawable.search
    )

    data object Cart : BottomNavItem(
        "Cart",
        R.drawable.cart
    )

    data object Heart : BottomNavItem(
        "Heart",
        R.drawable.heart
    )
}