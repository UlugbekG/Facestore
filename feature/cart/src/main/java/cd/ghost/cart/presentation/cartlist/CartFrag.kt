package cd.ghost.cart.presentation.cartlist

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import cd.ghost.cart.R
import cd.ghost.cart.databinding.FragCartBinding
import cd.ghost.common.Container
import cd.ghost.common.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import viewBinding

@AndroidEntryPoint
class CartFrag : Fragment(R.layout.frag_cart) {

    private val binding by viewBinding<FragCartBinding>()
    private val viewModel by viewModels<CartViewModel>()

    private val TAG = "CartFrag"

    private lateinit var cartAdapter: CartAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewModel.message.observeEvent(viewLifecycleOwner) {
            Toast.makeText(requireContext(), getString(it), Toast.LENGTH_SHORT).show()
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.cart.observe(viewLifecycleOwner) {
                    when (it) {
                        is Container.Error -> {
                            Log.d(TAG, "onViewCreated:Error:${it.error} ")
                        }

                        is Container.Pending -> {
                            Log.d(TAG, "onViewCreated:Pending ")
                        }

                        is Container.Success -> {
                            Log.d(TAG, "onViewCreated:Success:${it.value}")
                            cartAdapter.submitList(it.value.items)
                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(viewModel)
        binding.recyclerViewCart.adapter = cartAdapter
        binding.recyclerViewCart.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

    }

}