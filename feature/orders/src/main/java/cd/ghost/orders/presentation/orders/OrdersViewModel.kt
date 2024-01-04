package cd.ghost.orders.presentation.orders

import android.util.Log
import androidx.lifecycle.MutableLiveData
import cd.ghost.orders.domain.GetOrdersUseCase
import cd.ghost.orders.domain.entity.OrderEntity
import cd.ghost.presentation.BaseViewModel
import cd.ghost.presentation.live.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val getOrdersUseCase: GetOrdersUseCase,
) : BaseViewModel() {

    private val TAG = "OrdersViewModel"

    private val _orders = MutableLiveData<List<OrderEntity>>()
    val orders = _orders.asLiveData()

    fun getOrders() {
        viewModelScope.launch {
            val ordersList = getOrdersUseCase.getList()
            _orders.value = ordersList
            Log.d(TAG, "getOrders: $ordersList")
        }
    }

}