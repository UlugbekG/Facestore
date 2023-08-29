package cd.ghost.catalog.presentation.favorites

import cd.ghost.catalog.CatalogRouter
import cd.ghost.catalog.domain.GetFavoritesUseCase
import cd.ghost.catalog.domain.entity.ProductEntity
import cd.ghost.catalog.presentation.detail.DetailFragment
import cd.ghost.catalog.presentation.productlist.OnClickListener
import cd.ghost.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    getFavoritesUseCase: GetFavoritesUseCase,
    private val router: CatalogRouter,
) : BaseViewModel(), OnClickListener {

    val products = getFavoritesUseCase()

    override fun onClick(item: ProductEntity) {
        router.navigateToDetailScreen(DetailFragment.DetailArg(item.id))
    }

}