package cd.ghost.catalog.presentation.productlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import cd.ghost.catalog.domain.GetAllProductsUseCase
import cd.ghost.catalog.domain.GetProductsByCategoryUseCase
import cd.ghost.catalog.domain.entity.Category
import cd.ghost.catalog.domain.entity.EntityProduct
import cd.ghost.catalog.domain.entity.FilterData
import cd.ghost.catalog.domain.entity.SortType
import cd.ghost.common.MutableLiveEvent
import cd.ghost.common.asLiveData
import cd.ghost.common.publish
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getProductsByCategoryUseCase: GetProductsByCategoryUseCase,
) : ViewModel(), OnClickListener {

    private val _filter = MutableLiveData<FilterData>()
    val filter = _filter.asLiveData()

    private val _errorMessage = MutableLiveEvent<String>()
    val errorMessage = _errorMessage.asLiveData()

    private val _navigateToDetail = MutableLiveEvent<Int>()
    val navigateToDetail = _navigateToDetail.asLiveData()

    private val _navigateToFilter = MutableLiveEvent<FilterData>()
    val navigateToFilter = _navigateToFilter.asLiveData()

    init {
        initialFilterState()
    }

    private fun initialFilterState() {
        _filter.value = FilterData(
            sort = SortType.ASC,
            itemsSize = 10,
            category = Category.ALL
        )
    }

    val products = _filter.switchMap { filter ->
        if (filter.category == Category.ALL) getAllProductsUseCase(
            sort = filter.sort,
            limit = filter.itemsSize
        ).asLiveData()
        else getProductsByCategoryUseCase(
            category = filter.category,
            sort = filter.sort,
            limit = filter.itemsSize
        ).asLiveData()
    }


    fun navigateToFilter() {
        if (_filter.value == null) {
            initialFilterState()
        }
        _navigateToFilter.publish(_filter.value!!)
    }

    override fun onClick(item: EntityProduct) {
        val id = item.id ?: return // TODO: must be fixed
        _navigateToDetail.publish(id)
    }

    override fun onLongClick(item: EntityProduct) {
        // TODO: must be initialized. while long click product should be in cart.
    }

    fun setFilterResult(result: FilterData?) {
        if (result == null) return
        _filter.value = result!!
    }

}