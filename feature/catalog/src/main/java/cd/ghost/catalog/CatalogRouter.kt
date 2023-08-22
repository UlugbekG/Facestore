package cd.ghost.catalog

import android.os.Bundle

interface CatalogRouter {

    fun navigateToDetailScreen(args: Bundle)

    fun navigateToFilterScreen(args: Bundle)

}