package cd.ghost.catalog.presentation.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import cd.ghost.catalog.R
import cd.ghost.catalog.databinding.FragmentFavoritesBinding
import cd.ghost.catalog.presentation.productlist.ProductsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import viewBinding

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val binding: FragmentFavoritesBinding by viewBinding()
    private val viewModel by viewModels<FavoritesViewModel>()
    private val adapter by lazy { ProductsAdapter(viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.products.collectLatest {
                    adapter.submitList(it)
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