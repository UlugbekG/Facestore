package cd.ghost.catalog.presentation.productlist

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import cd.ghost.catalog.R
import cd.ghost.catalog.databinding.FragProductsBinding
import cd.ghost.catalog.domain.entity.FilterData
import cd.ghost.catalog.presentation.detail.DetailFrag
import cd.ghost.catalog.presentation.detail.DetailFrag.Companion.DETAIL_ARG
import cd.ghost.catalog.presentation.filter.FilterFrag
import cd.ghost.catalog.presentation.filter.FilterFrag.Companion.FILTER_ARG
import cd.ghost.common.Container
import cd.ghost.common.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import viewBinding
import javax.inject.Inject


@AndroidEntryPoint
class ProductsFrag : Fragment(R.layout.frag_products) {

    private val TAG = "ProductsFragment"

    private val viewModel by viewModels<ProductsViewModel>()
    private val binding by viewBinding<FragProductsBinding>()
    private val adapter by lazy { ProductsAdapter(viewModel) }

    @Inject
    lateinit var destinations: ProductsDestinationId

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        filterResultReceiver()

        binding.btnFilter.setOnClickListener {
            viewModel.navigateToFilter()
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.products.observe(viewLifecycleOwner) {
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

        viewModel.navigateToDetail.observeEvent(viewLifecycleOwner) { productId ->
            destinations.provideTopNavController().navigate(
                resId = destinations.actionToDetail,
                args = bundleOf(DETAIL_ARG to DetailFrag.DetailArg(productId))
            )
        }

        viewModel.navigateToFilter.observeEvent(viewLifecycleOwner) { filterData ->
            findNavController().navigate(
                resId = destinations.actionToFilter,
                args = bundleOf(FILTER_ARG to FilterFrag.FilterArg(filterData))
            )
        }

        viewModel.errorMessage.observeEvent(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

    }

    private fun filterResultReceiver() {
        findNavController()
            .currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<FilterData>(FILTER_ARG)
            ?.observe(viewLifecycleOwner) { result ->
                viewModel.setFilterResult(result)
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