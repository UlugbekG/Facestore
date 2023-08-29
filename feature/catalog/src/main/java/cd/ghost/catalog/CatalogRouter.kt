package cd.ghost.catalog

import cd.ghost.catalog.presentation.detail.DetailFragment
import cd.ghost.catalog.presentation.filter.FilterFragment

interface CatalogRouter {

    fun navigateToDetailScreen(args: DetailFragment.DetailArg)

    fun navigateToFilterScreen(args: FilterFragment.FilterArg)

}