package cd.ghost.catalog.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import cd.ghost.catalog.R
import cd.ghost.catalog.databinding.FragmentProductsBinding
import cd.ghost.catalog.domain.entity.EntityProduct
import cd.ghost.common.Container
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import viewBinding

@AndroidEntryPoint
class ProductsFragment : Fragment(R.layout.fragment_products), OnClickListener {

    private val TAG = "ProductsFragment"

    private val viewModel by viewModels<ProductsViewModel>()
    private val binding by viewBinding<FragmentProductsBinding>()
    private val adapter by lazy { ProductsAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchProducts()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            root.adapter = adapter
            root.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.products.collectLatest {
                    when (it) {
                        is Container.Success -> {
                            adapter.submitList(it.data)
                            Log.d(TAG, "Success: ${it.data}")
                        }

                        is Container.Error -> {
                            Log.d(TAG, "Error: ${it.error}")
                        }

                        is Container.Pending -> {
                            Log.d(TAG, "Pending: $it")
                        }

                        else -> {
                            Log.d(TAG, "some error: ")
                        }
                    }
                }
            }
        }
    }

    override fun onClick(item: EntityProduct) {

    }

    override fun onLongClick(item: EntityProduct) {

    }
}