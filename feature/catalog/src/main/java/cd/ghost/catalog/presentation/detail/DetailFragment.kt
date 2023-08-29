package cd.ghost.catalog.presentation.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import cd.ghost.catalog.R
import cd.ghost.catalog.databinding.FragmentDetailBinding
import cd.ghost.common.serializable
import cd.ghost.presentation.view.observe
import coil.load
import com.google.android.material.elevation.SurfaceColors
import dagger.hilt.android.AndroidEntryPoint
import viewBinding
import java.io.Serializable

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    class DetailArg(
        val productId: Int
    ) : Serializable

    private val binding by viewBinding<FragmentDetailBinding>()
    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val productId = it.serializable<DetailArg>(DETAIL_ARG)?.productId
            viewModel.getProduct(productId)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().window.navigationBarColor =
            SurfaceColors.SURFACE_0.getColor(requireContext())

        binding.apply {
            setupListeners()
            observeState()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun FragmentDetailBinding.observeState() {
        detailResultView.observe(viewLifecycleOwner, viewModel.state) { state ->
            val product = state.product

            val favImg =
                if (state.isInFavorite) R.drawable.ic_star_24 else R.drawable.ic_star_off_24

            contentImage.load(product.imageUrl) { crossfade(true) }
            btnFavorite.setImageResource(favImg)
            tvTitle.text = product.title
            tvPrice.text = "$${product.price}"
            tvDescription.text = product.description
            topAppBar.title = product.title
            progressAddToCart.isVisible = state.showAddToCartProgress
            btnAddToCart.isVisible = state.showAddToCartButton
            btnAddToCart.isEnabled = state.enableAddToCartButton
            btnAddToCart.text = getText(state.addToCartTextRes)
        }
    }

    private fun FragmentDetailBinding.setupListeners() {
        topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        btnAddToCart.setOnClickListener {
            viewModel.addToCart()
        }

        btnFavorite.setOnClickListener {
            viewModel.favoriteAction()
        }
    }

    companion object {
        const val DETAIL_ARG = "detail_arg"
    }
}



