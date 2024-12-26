package com.example.mykotlin.utils

object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME_CONSUMER = "homeConsumer"
    const val ESTABLISHMENT_DETAIL = "establishmentDetail/{id}"
    const val USER_PROFILE = "userProfile"
    const val ESTABLISHMENT_SCREEN = "establishmentScreen"
}

fun getRouteTitle(route: String?): String {
    return when (route) {
        Routes.LOGIN -> "Login"
        Routes.REGISTER -> "Register"
        Routes.HOME_CONSUMER -> "Home"
        Routes.ESTABLISHMENT_DETAIL -> "Establishment Details"
        Routes.USER_PROFILE -> "User Profile"
        Routes.ESTABLISHMENT_SCREEN -> "Establishments"
        else -> "Unknown"
    }
}
