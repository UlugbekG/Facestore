package cd.ghost.cart.presentation.cartlist

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cd.ghost.cart.R
import cd.ghost.cart.databinding.FragmentCartBinding
import cd.ghost.presentation.view.observe
import dagger.hilt.android.AndroidEntryPoint
import viewBinding

@AndroidEntryPoint
class CartFragment : Fragment(R.layout.fragment_cart) {

    private val binding by viewBinding<FragmentCartBinding>()
    private val viewModel by viewModels<CartViewModel>()

    private lateinit var cartAdapter: CartAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupListeners()
        binding.setupActions()
        binding.observeState()
    }

    private fun setupListeners() {
        binding.btnOrder.setOnClickListener {
            viewModel.navigateToOrderScreen()
        }
    }

    private fun FragmentCartBinding.observeState() {
        root.observe(viewLifecycleOwner, viewModel.state) { state ->
            cartAdapter.submitList(state.cartItems)
            binding.tvTotalPrice.text = state.totalPrice
            binding.showDetailsAction.root.isVisible = state.showDetailsAction
            binding.deleteAction.root.isVisible = state.showDeleteAction
            binding.editQuantityAction.root.isVisible = state.showEditQuantityAction
            binding.actionsPanel.isVisible = state.showActionsPanel
            binding.btnOrder.isVisible = state.visibilityOrderButton
            binding.btnOrder.isEnabled = state.enableCreateOrderButton
            onBackPressHandler(state.onBackPressEnabled)
        }
    }

    private fun FragmentCartBinding.setupActions() {
        deleteAction.actionImageView.setImageResource(R.drawable.ic_delete)
        deleteAction.actionTextView.setText(R.string.cart_action_delete)

        showDetailsAction.actionImageView.setImageResource(R.drawable.ic_details)
        showDetailsAction.actionTextView.setText(R.string.cart_action_details)

        editQuantityAction.actionImageView.setImageResource(R.drawable.ic_edit)
        editQuantityAction.actionTextView.setText(R.string.cart_action_edit)
    }

    private fun onBackPressHandler(onBackPressEnabled: Boolean) {
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (onBackPressEnabled) {
                    viewModel.disableSelectionMode()
                } else {
                    findNavController().navigateUp()
                }
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner, callback
        )
    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(viewModel)
        binding.recyclerViewCart.adapter = cartAdapter
        binding.recyclerViewCart.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

}