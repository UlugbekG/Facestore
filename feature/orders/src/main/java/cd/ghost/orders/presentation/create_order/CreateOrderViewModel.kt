package cd.ghost.orders.presentation.create_order

import android.util.Log
import cd.ghost.common.AppException
import cd.ghost.orders.domain.CreateOrderUseCase
import cd.ghost.orders.domain.exceptions.EmptyException
import cd.ghost.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateOrderViewModel @Inject constructor(
    private val createOrderUseCase: CreateOrderUseCase
) : BaseViewModel() {

    private val TAG = "CreateOrderViewModel"

    fun createOrders(
        firstname: String?,
        lastname: String?,
        address: String?,
        comment: String?,
    ) {
        viewModelScope.launch {
            try {
                createOrderUseCase(firstname, lastname, address, comment)
            } catch (e: EmptyException) {
                Log.d(TAG, "createOrders: $e")
            } catch (e: AppException) {
                Log.d(TAG, "createOrders: $e")
            }
        }
    }
}