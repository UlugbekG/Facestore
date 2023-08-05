package cd.ghost.catalog.presentation.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import cd.ghost.catalog.R
import cd.ghost.catalog.databinding.FragDetailBinding
import cd.ghost.common.observeEvent
import cd.ghost.common.utils.serializable
import dagger.hilt.android.AndroidEntryPoint
import viewBinding
import java.io.Serializable

@AndroidEntryPoint
class DetailFrag : Fragment(R.layout.frag_detail) {

    class DetailArg(
        val productId: Int
    ) : Serializable

    private val binding by viewBinding<FragDetailBinding>()
    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val productId = it.serializable<DetailArg>(DETAIL_ARG)?.productId
            viewModel.initProductId(productId)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }

            viewModel.message.observeEvent(viewLifecycleOwner) {
                Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val DETAIL_ARG = "detail_arg"
    }
}