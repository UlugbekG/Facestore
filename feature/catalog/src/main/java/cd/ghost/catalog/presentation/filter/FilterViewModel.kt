package cd.ghost.catalog.presentation.filter

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
) : ViewModel() {

    fun setCategory() {
//        router.back(FilterData("CATEGORY"))
    }

    fun setCount() {
//        router.back(FilterData("COUNT"))
    }

    fun setSort() {
//        router.back(FilterData("SORT"))
    }

}