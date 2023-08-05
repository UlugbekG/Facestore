package cd.ghost.catalog.presentation.productlist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import cd.ghost.catalog.R
import cd.ghost.catalog.databinding.FragProductsBinding
import cd.ghost.common.Container
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import viewBinding


@AndroidEntryPoint
class ProductsFragment : Fragment(R.layout.frag_products) {

    private val TAG = "ProductsFragment"

    private val viewModel by viewModels<ProductsViewModel>()
    private val binding by viewBinding<FragProductsBinding>()
    private val adapter by lazy { ProductsAdapter(viewModel) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchProducts()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

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

    private fun setupRecyclerView() {
        binding.apply {
            recyclerView.adapter = adapter
            val displayMetrics = resources.displayMetrics
            val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
            val spanCount = (screenWidthDp / 180).toInt() // adjust 180 to your desired item width
            val layoutManager = GridLayoutManager(context, spanCount)
            recyclerView.layoutManager = layoutManager
        }
    }
}