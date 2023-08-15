package cd.ghost.fakestore.features.catalog

import androidx.navigation.NavController
import cd.ghost.catalog.CatalogRouter
import cd.ghost.fakestore.R
import cd.ghost.fakestore.main.NavControllerHolder
import javax.inject.Inject


class AdapterCatalogRouter @Inject constructor(
    private val controllerHolder: NavControllerHolder
) : CatalogRouter {

    override fun provideTopNavController(): NavController =
        controllerHolder.rootNavController()

    override val actionToDetail: Int
        get() = R.id.action_tabsFrag_to_detailFrag

    override val actionToFilter: Int
        get() = R.id.action_productsFragment_to_filterFrag

}