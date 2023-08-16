package cd.ghost.cart.presentation.cartlist

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cd.ghost.cart.R
import cd.ghost.cart.databinding.FragCartBinding
import cd.ghost.common.Container
import cd.ghost.presentation.live.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
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

        binding.setupActions()

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
                            val state = it.value
                            cartAdapter.submitList(state.cartItems)
                            binding.tvTotalPrice.text = state.totalPrice
                            binding.showDetailsAction.root.isVisible = state.showDetailsAction
                            binding.deleteAction.root.isVisible = state.showDeleteAction
                            binding.editQuantityAction.root.isVisible = state.showEditQuantityAction
                            binding.actionsPanel.isVisible = state.showActionsPanel
                            binding.btnOrder.isEnabled = state.enableCreateOrderButton
                            onBackPressHandler(state.onBackPressEnabled)
                        }
                    }
                }
            }
        }
    }

    private fun FragCartBinding.setupActions() {
        deleteAction.actionImageView.setImageResource(R.drawable.ic_delete)
        deleteAction.actionTextView.setText(R.string.cart_action_delete)

        showDetailsAction.actionImageView.setImageResource(R.drawable.ic_details)
        showDetailsAction.actionTextView.setText(R.string.cart_action_details)

        editQuantityAction.actionImageView.setImageResource(R.drawable.ic_edit)
        editQuantityAction.actionTextView.setText(R.string.cart_action_edit)
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