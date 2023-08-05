package cd.ghost.catalog.presentation.filter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import cd.ghost.catalog.R
import cd.ghost.catalog.databinding.FragFilterBinding
import dagger.hilt.android.AndroidEntryPoint
import viewBinding
import java.io.Serializable

@AndroidEntryPoint
class FilterFrag : Fragment(R.layout.frag_filter) {

    class FilterArg(
        val filterData: FilterData
    ) : Serializable

    private val binding by viewBinding<FragFilterBinding>()
    private val viewModel by viewModels<FilterViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvCategory.setOnClickListener {
                viewModel.setCategory()
            }
            tvCount.setOnClickListener {
                viewModel.setCount()
            }
            tvSort.setOnClickListener {
                viewModel.setSort()
            }
        }
    }

    companion object {
        const val FILTER_ARG = "filter_arg"
    }
}