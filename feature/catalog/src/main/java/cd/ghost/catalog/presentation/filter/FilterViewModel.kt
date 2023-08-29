package cd.ghost.catalog.presentation.filter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.ghost.catalog.domain.GetCategoriesUseCase
import cd.ghost.catalog.domain.entity.Category
import cd.ghost.catalog.domain.entity.FilterData
import cd.ghost.catalog.domain.entity.SortType
import cd.ghost.common.Container
import cd.ghost.presentation.live.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _state = MutableLiveData<Container<State>>()
    val state = _state.asLiveData()

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

    init {
        _state.value = Container.Pending
    }

    fun getFilterData(): FilterData? = _filter.value

    fun fetchCategories() {
        viewModelScope.launch {
            getCategoriesUseCase.getCategories()
                .map { container ->
                    container.map {
                        State(it)
                    }
                }
                .collectLatest {
                    _state.value = it
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

    data class State(
        val categories: List<String>
    )
}

