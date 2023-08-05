package cd.ghost.cart.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import cd.ghost.cart.R
import cd.ghost.cart.databinding.FragCartBinding
import viewBinding

class CartFrag : Fragment(R.layout.frag_cart) {

    private val binding by viewBinding<FragCartBinding>()
    private val viewModel by viewModels<CartViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}