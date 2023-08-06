package cd.ghost.catalog.presentation.filter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.ghost.catalog.domain.GetCategoriesUseCase
import cd.ghost.catalog.domain.entity.Category
import cd.ghost.catalog.domain.entity.FilterData
import cd.ghost.catalog.domain.entity.SortType
import cd.ghost.common.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _categories = MutableLiveData<List<String?>>()
    val categories = _categories.asLiveData()

    private val _filter = MutableLiveData<FilterData>()
    val filter = _filter.asLiveData()

    fun setFilter(filter: FilterData?) {
        if (filter == null) {
            _filter.value = FilterData(
                sort = SortType.ASC,
                itemsSize = 20,
                category = Category.ELECTRONICS
            )
            return
        }
        _filter.value = filter!!
    }

    fun getFilterData(): FilterData? = _filter.value

    fun fetchCategories() {
        viewModelScope.launch {
            getCategoriesUseCase
                .invoke()
                .collectLatest {
                    _categories.value = ArrayList(it).apply { add("all") }
                }
        }
    }

    fun setSortId(sortType: SortType) {
        _filter.value = _filter.value?.copy(
            sort = sortType
        )
    }

    fun setItemSize(size: Int) {
        _filter.value = _filter.value?.copy(
            itemsSize = size
        )
    }

    fun setCategory(category: Category) {
        _filter.value = _filter.value?.copy(
            category = category
        )
    }

}

