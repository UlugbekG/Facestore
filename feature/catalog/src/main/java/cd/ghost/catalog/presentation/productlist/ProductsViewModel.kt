package cd.ghost.catalog.presentation.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.ghost.catalog.domain.ProductsListUseCase
import cd.ghost.catalog.domain.entity.EntityProduct
import cd.ghost.catalog.presentation.filter.FilterData
import cd.ghost.common.Container
import cd.ghost.common.MutableLiveEvent
import cd.ghost.common.asLiveData
import cd.ghost.common.publish
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepo: ProductsListUseCase
) : ViewModel(), OnClickListener {

    private val _products = MutableStateFlow<Container<List<EntityProduct>>>(Container.Pending)
    val products: StateFlow<Container<List<EntityProduct>>> = _products

    private val _errorMessage = MutableLiveEvent<String>()
    val errorMessage = _errorMessage.asLiveData()

    private val _filter = MutableLiveEvent<FilterData>()
    val filter = _filter.asLiveData()

    private val _navigateToDetail = MutableLiveEvent<Int>()
    val navigateToDetail = _navigateToDetail.asLiveData()

    private val _navigateToFilter = MutableLiveEvent<FilterData>()
    val navigateToFilter = _navigateToFilter.asLiveData()

    fun fetchProducts() {
        viewModelScope.launch {
            productsRepo(sort = "desc")
                .collectLatest {
                    _products.value = it
                }
        }
    }

    fun navigateToFilter() {
        _navigateToFilter.publish(FilterData(null))
    }

    override fun onClick(item: EntityProduct) {
        val id = item.id ?: return // TODO: must be fixed
        _navigateToDetail.publish(id)
    }

    override fun onLongClick(item: EntityProduct) {
        // TODO: must be initialized. while long click product should be in cart.
    }

}