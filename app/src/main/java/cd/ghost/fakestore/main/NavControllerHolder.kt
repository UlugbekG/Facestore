package cd.ghost.fakestore.main

import androidx.navigation.NavController

interface NavControllerHolder {

    fun rootNavController(): NavController?

    fun getHomeNavController(): NavController?

    fun getCartsNavController(): NavController?

    fun getProfileNavController(): NavController?

}