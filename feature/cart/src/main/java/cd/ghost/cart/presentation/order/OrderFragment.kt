package cd.ghost.cart.presentation.order

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import cd.ghost.cart.R
import cd.ghost.cart.databinding.FragmentOrderBinding
import dagger.hilt.android.AndroidEntryPoint
import viewBinding

@AndroidEntryPoint
class OrderFragment : Fragment(R.layout.fragment_order) {

    private val viewModel by viewModels<OrderViewModel>()
    private val binding by viewBinding<FragmentOrderBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}