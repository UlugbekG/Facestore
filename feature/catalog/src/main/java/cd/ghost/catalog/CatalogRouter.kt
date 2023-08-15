package cd.ghost.catalog

import androidx.annotation.IdRes
import androidx.navigation.NavController

interface CatalogRouter {

    fun provideTopNavController(): NavController

    @get:IdRes
    val actionToDetail: Int

    @get:IdRes
    val actionToFilter: Int
}