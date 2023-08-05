package cd.ghost.catalog.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import cd.ghost.catalog.R
import cd.ghost.catalog.databinding.FragDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import viewBinding
import javax.inject.Inject

@AndroidEntryPoint
class DetailFrag : Fragment(R.layout.frag_detail) {

    private val binding by viewBinding<FragDetailBinding>()
    private val viewModel by viewModels<DetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnBack.setOnClickListener {
                viewModel.back()
            }
        }
    }
}