package cd.ghost.cart.presentation.orders

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import cd.ghost.cart.R
import cd.ghost.cart.databinding.FragmentOrdersBinding
import dagger.hilt.android.AndroidEntryPoint
import viewBinding

@AndroidEntryPoint
class OrdersFragment : Fragment(R.layout.fragment_orders) {

    private val viewModel by viewModels<OrdersViewModel>()
    private val binding by viewBinding<FragmentOrdersBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}