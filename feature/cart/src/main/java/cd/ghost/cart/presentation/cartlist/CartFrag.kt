package cd.ghost.cart.presentation.cartlist

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cd.ghost.cart.R
import cd.ghost.cart.databinding.FragCartBinding
import cd.ghost.cart.presentation.CartRouter
import cd.ghost.common.Container
import cd.ghost.common.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import viewBinding
import javax.inject.Inject


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
                viewModel.state.collectLatest {
                    when (it) {
                        is Container.Error -> {
                            Log.d(TAG, "onViewCreated:Error:${it.error} ")
                        }

                        is Container.Pending -> {
                            Log.d(TAG, "onViewCreated:Pending ")
                        }

                        is Container.Success -> {
                            cartAdapter.submitList(it.value.cartItems)
                            binding.tvTotalPrice.text = it.value.totalPrice
                            onBackPressHandler(it.value.onBackPressEnabled)
                        }
                    }
                }
            }
        }
    }

    private fun onBackPressHandler(onBackPressEnabled: Boolean) {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (onBackPressEnabled) {
                        viewModel.disableSelectionMode()
                    } else {
                        findNavController().navigateUp()
                    }
                }
            }
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            callback
        )
    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(viewModel)
        binding.recyclerViewCart.adapter = cartAdapter
        binding.recyclerViewCart.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

    }

}