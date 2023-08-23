package cd.ghost.catalog.presentation.favorites

import cd.ghost.catalog.domain.GetFavoritesUseCase
import cd.ghost.catalog.domain.entity.ProductEntity
import cd.ghost.catalog.presentation.productlist.OnClickListener
import cd.ghost.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase
) : BaseViewModel(), OnClickListener {


    val products = getFavoritesUseCase()

    override fun onClick(item: ProductEntity) {
        TODO("Not yet implemented")
    }

    override fun onLongClick(item: ProductEntity) {
        TODO("Not yet implemented")
    }


}