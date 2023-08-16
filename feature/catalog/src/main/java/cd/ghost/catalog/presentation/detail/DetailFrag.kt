package cd.ghost.catalog.presentation.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import cd.ghost.catalog.R
import cd.ghost.catalog.databinding.FragDetailBinding
import cd.ghost.common.Container
import cd.ghost.presentation.live.observeEvent
import cd.ghost.common.serializable
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import viewBinding
import java.io.Serializable

@AndroidEntryPoint
class DetailFrag : Fragment(R.layout.frag_detail) {

    private val TAG = "DetailFrag"

    class DetailArg(
        val productId: Int
    ) : Serializable

    private val binding by viewBinding<FragDetailBinding>()
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
        binding.apply {

            viewModel.message.observeEvent(viewLifecycleOwner) {
                Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
            }

            topAppBar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            btnAddToCart.setOnClickListener {
                viewModel.addToCart()
            }

            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    viewModel.state.collectLatest { value ->
                        when (value) {
                            is Container.Error -> {
                                Log.d(TAG, "Container Error: ${value.error}")
                            }

                            is Container.Pending -> {
                                Log.d(TAG, "Container Pending: ...")
                            }

                            is Container.Success -> {
                                Log.d(TAG, "Container Success: ${value.value}")
                                val data = value.value.product
                                contentImage.load(data.imageUrl) {
                                    crossfade(true)
                                }

                                tvTitle.text = data.title
                                tvPrice.text = "$${data.price}"
                                tvDescription.text =
                                    data.description + "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\n" +
                                            "\n" +
                                            "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham." + "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\n" +
                                            "\n" +
                                            "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham." + "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\n" +
                                            "\n" +
                                            "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham."
                                topAppBar.title = data.title
                                btnAddToCart.isEnabled = value.value.enableAddToCartButton
                                btnAddToCart.text = getText(value.value.addToCartTextRes)
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val DETAIL_ARG = "detail_arg"
    }
}