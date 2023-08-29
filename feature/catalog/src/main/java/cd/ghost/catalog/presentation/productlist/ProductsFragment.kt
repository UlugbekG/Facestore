package cd.ghost.catalog.presentation.productlist

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import cd.ghost.catalog.R
import cd.ghost.catalog.databinding.FragmentProductsBinding
import cd.ghost.catalog.domain.entity.FilterData
import cd.ghost.catalog.presentation.filter.FilterFragment.Companion.FILTER_ARG
import cd.ghost.presentation.live.observeEvent
import cd.ghost.presentation.view.observe
import dagger.hilt.android.AndroidEntryPoint
import viewBinding


@AndroidEntryPoint
class ProductsFragment : Fragment(R.layout.fragment_products) {

    private val viewModel by viewModels<ProductsViewModel>()
    private val binding by viewBinding<FragmentProductsBinding>()
    private val adapter by lazy { ProductsAdapter(viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        filterResultReceiver()
        setupListeners()

        binding.observeState()

        viewModel.errorMessage.observeEvent(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

    }

    private fun setupListeners() = binding.apply {
        btnFilter.setOnClickListener {
            viewModel.navigateToFilter()
        }

        mainView.setTryAgainListener {
            viewModel.reload()
        }
    }

    private fun filterResultReceiver() = findNavController()
        .currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<FilterData>(FILTER_ARG)
        ?.observe(viewLifecycleOwner) { result ->
            viewModel.setFilterResult(result)
        }

    private fun setupRecyclerView() = binding.apply {
        recyclerView.adapter = adapter
        val displayMetrics = resources.displayMetrics
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        val spanCount = (screenWidthDp / 180).toInt() // adjust 180 to your desired item width
        val layoutManager = GridLayoutManager(context, spanCount)
        recyclerView.layoutManager = layoutManager
    }

    private fun FragmentProductsBinding.observeState() {
        mainView.observe(viewLifecycleOwner, viewModel.products) {
            adapter.submitList(it)
        }
    }
}