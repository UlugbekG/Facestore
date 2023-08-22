package cd.ghost.fakestore.main.navigation

import androidx.navigation.NavController

interface NavControllerHolder {

    fun getRootNavController(): NavController?

    fun getHomeNavController(): NavController?

    fun getCartsNavController(): NavController?

    fun getProfileNavController(): NavController?

}