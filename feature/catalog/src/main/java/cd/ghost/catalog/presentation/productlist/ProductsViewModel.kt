package cd.ghost.catalog.presentation.productlist

import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import cd.ghost.catalog.CatalogRouter
import cd.ghost.catalog.domain.GetAllProductsUseCase
import cd.ghost.catalog.domain.GetCategoriesUseCase
import cd.ghost.catalog.domain.GetProductsByCategoryUseCase
import cd.ghost.catalog.domain.entity.Category
import cd.ghost.catalog.domain.entity.FilterData
import cd.ghost.catalog.domain.entity.ProductEntity
import cd.ghost.catalog.domain.entity.SortType
import cd.ghost.catalog.presentation.detail.DetailFragment
import cd.ghost.catalog.presentation.filter.FilterFragment
import cd.ghost.common.Container
import cd.ghost.presentation.live.MutableLiveEvent
import cd.ghost.presentation.live.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getProductsByCategoryUseCase: GetProductsByCategoryUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val router: CatalogRouter
) : ViewModel(), OnClickListener {

    private val _filter = MutableLiveData<FilterData>()
    val filter = _filter.asLiveData()

    val products = _filter.switchMap { getProductsFromUseCases(it) }.asFlow()

    private val _errorMessage = MutableLiveEvent<String>()
    val errorMessage = _errorMessage.asLiveData()

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

    private fun getProductsFromUseCases(
        filter: FilterData
    ): LiveData<Container<List<ProductEntity>>> {
        return if (filter.category == Category.ALL) {
            getAllProductsUseCase(
                filter = filter
            ).asLiveData()
        } else {
            getProductsByCategoryUseCase(
                filter = filter
            ).asLiveData()
        }
    }

    fun navigateToFilter() {
        val filterData = _filter.value ?: return
        router.navigateToFilterScreen(
            args = bundleOf(FilterFragment.FILTER_ARG to FilterFragment.FilterArg(filterData))
        )
    }

    override fun onClick(item: ProductEntity) {
        router.navigateToDetailScreen(
            args = bundleOf(DetailFragment.DETAIL_ARG to DetailFragment.DetailArg(item.id))
        )
    }

    override fun onLongClick(item: ProductEntity) {

    }

    fun setFilterResult(result: FilterData?) {
        if (result == null) return
        _filter.value = result!!
    }

}