package cd.ghost.fakestore.main.tabs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cd.ghost.common.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TabsViewModel @Inject constructor(

) : ViewModel() {

    private val _orders = MutableLiveData(0)
    val orders = _orders.asLiveData()

    init {
        _orders.value = 3
    }
}