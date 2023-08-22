package cd.ghost.fakestore.features.catalog

import android.os.Bundle
import cd.ghost.catalog.CatalogRouter
import cd.ghost.fakestore.R
import cd.ghost.fakestore.main.NavControllerHolder
import javax.inject.Inject


class AdapterCatalogRouter @Inject constructor(
    private val navControllerHolder: NavControllerHolder
) : CatalogRouter {

    override fun navigateToDetailScreen(args: Bundle) {
        navControllerHolder.rootNavController()?.navigate(
            resId = R.id.action_tabsFrag_to_detailFrag,
            args = args
        )
    }

    override fun navigateToFilterScreen(args: Bundle) {
        navControllerHolder.getHomeNavController()?.navigate(
            resId = R.id.action_productsFragment_to_filterFrag,
            args = args
        )
    }

}