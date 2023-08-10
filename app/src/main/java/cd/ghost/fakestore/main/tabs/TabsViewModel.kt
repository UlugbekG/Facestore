package cd.ghost.fakestore.main.tabs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.ghost.common.asLiveData
import cd.ghost.fakestore.main.repos.CartItemsCountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TabsViewModel @Inject constructor(
    private val cartItemsCountRepository: CartItemsCountRepository
) : ViewModel() {

    private val _cartItems = MutableLiveData(0)
    val cartItems = _cartItems.asLiveData()

    init {
        viewModelScope.launch {
            cartItemsCountRepository
                .getCartItemsCount()
                .collectLatest {
                    _cartItems.value = it
                }
        }
    }
}