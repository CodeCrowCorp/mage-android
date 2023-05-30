package io.codecrow.mage.ui.components

sealed class Screen(val route: String){
    object BrowseScreen : Screen("main")
    object ChannelScreen : Screen("channel")
}
