package cd.ghost.catalog.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.ghost.catalog.domain.ProductsListUseCase
import cd.ghost.catalog.domain.entity.EntityProduct
import cd.ghost.common.Container
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepo: ProductsListUseCase
) : ViewModel() {

    private val _products = MutableStateFlow<Container<List<EntityProduct>>>(Container.Pending)
    val products: StateFlow<Container<List<EntityProduct>>> = _products

    private val _errorMessage = MutableStateFlow<List<EntityProduct>>(emptyList())
    val errorMessage: StateFlow<List<EntityProduct>> = _errorMessage

    fun fetchProducts() {
        viewModelScope.launch {
            productsRepo(sort = "desc")
                .collectLatest {
                    _products.value = it
                }
        }
    }
}