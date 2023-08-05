package cd.ghost.fakestore.featuresbind.catalog

import cd.ghost.catalog.presentation.productlist.CatalogRouter
import cd.ghost.fakestore.NavComponentRouter
import cd.ghost.fakestore.R
import javax.inject.Inject


class AdapterCatalogRouter @Inject constructor(
    private val navComponentRouter: NavComponentRouter
) : CatalogRouter {

    override fun navigateToDetail(productId: Int?) {
        navComponentRouter.mainNavController().navigate(R.id.action_tabsFrag_to_detailFrag)
    }

}