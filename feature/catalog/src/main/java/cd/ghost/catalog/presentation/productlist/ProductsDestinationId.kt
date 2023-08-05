package cd.ghost.catalog.presentation.productlist

import androidx.annotation.IdRes
import androidx.navigation.NavController

interface ProductsDestinationId {

    fun provideTopNavController(): NavController

    @get:IdRes
    val actionToDetail: Int

    @get:IdRes
    val actionToFilter: Int
}