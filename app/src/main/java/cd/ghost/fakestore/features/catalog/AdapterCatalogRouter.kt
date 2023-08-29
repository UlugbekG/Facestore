package cd.ghost.fakestore.features.catalog

import androidx.core.os.bundleOf
import cd.ghost.catalog.CatalogRouter
import cd.ghost.catalog.presentation.detail.DetailFragment
import cd.ghost.catalog.presentation.detail.DetailFragment.Companion.DETAIL_ARG
import cd.ghost.catalog.presentation.filter.FilterFragment
import cd.ghost.catalog.presentation.filter.FilterFragment.Companion.FILTER_ARG
import cd.ghost.fakestore.R
import cd.ghost.fakestore.main.navigation.NavControllerHolder
import javax.inject.Inject


class AdapterCatalogRouter @Inject constructor(
    private val navControllerHolder: NavControllerHolder
) : CatalogRouter {

    override fun navigateToDetailScreen(args: DetailFragment.DetailArg) {
        navControllerHolder.getRootNavController()?.navigate(
            resId = R.id.action_tabsFrag_to_detailFrag,
            args = bundleOf(DETAIL_ARG to args)
        )
    }

    override fun navigateToFilterScreen(args: FilterFragment.FilterArg) {
        navControllerHolder.getHomeNavController()?.navigate(
            resId = R.id.action_productsFragment_to_filterFrag,
            args = bundleOf(FILTER_ARG to args)
        )
    }

}