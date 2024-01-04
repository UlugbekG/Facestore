package cd.ghost.orders.presentation.create_order

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import cd.ghost.orders.R
import cd.ghost.orders.databinding.FragmentCreateOrderBinding
import dagger.hilt.android.AndroidEntryPoint
import viewBinding

@AndroidEntryPoint
class CreateOrderFragment : Fragment(R.layout.fragment_create_order) {

    private val binding by viewBinding<FragmentCreateOrderBinding>()
    private val viewModel by viewModels<CreateOrderViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.apply {
            btnCreateOrder.setOnClickListener {
                viewModel.createOrders(
                    firstname = edFirstname.text?.toString(),
                    lastname = edLastname.text?.toString(),
                    address = edAddress.text?.toString(),
                    comment = edComment.text?.toString()
                )
            }
        }
    }
}