package cd.ghost.catalog.presentation.filter

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import cd.ghost.catalog.R
import cd.ghost.catalog.databinding.FragmentFilterBinding
import cd.ghost.catalog.domain.entity.FilterData
import cd.ghost.catalog.domain.entity.SortType
import cd.ghost.common.serializable
import dagger.hilt.android.AndroidEntryPoint
import viewBinding
import java.io.Serializable


@AndroidEntryPoint
class FilterFragment : Fragment(R.layout.fragment_filter) {

    private val TAG = "FilterFrag"

    class FilterArg(
        val filterData: FilterData
    ) : Serializable

    private val binding by viewBinding<FragmentFilterBinding>()
    private val viewModel by viewModels<FilterViewModel>()
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            val filter = it?.serializable<FilterArg>(FILTER_ARG)?.filterData
            Log.d(TAG, "CurrentFilter: $filter")
            viewModel.setFilter(filter)
        }
        viewModel.fetchCategories()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCategoryRecyclerView()
        observeCategoryList()
        observeFilter()
        setupListeners()
    }

    private fun setupListeners() {
        binding.apply {
            appBar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            btn10.setOnClickListener {
                viewModel.setItemSize(10)
            }

            btn20.setOnClickListener {
                viewModel.setItemSize(20)
            }

            btnAsc.setOnClickListener {
                viewModel.setSortId(SortType.ASC)
            }

            btnDesc.setOnClickListener {
                viewModel.setSortId(SortType.DESC)
            }
            btnApply.setOnClickListener {
                applyFilter()
            }
        }
    }

    private fun applyFilter() {
        Log.d(TAG, "setupListeners: ${viewModel.getFilterData()}")
        findNavController()
            .previousBackStackEntry
            ?.savedStateHandle
            ?.set(FILTER_ARG, viewModel.getFilterData())
        findNavController().popBackStack()
    }


    private fun observeFilter() {
        viewModel.filter.observe(viewLifecycleOwner) { filter ->
            binding.grItemsSize.check(filter.getItemSizeRadioBtn())
            binding.grSortType.check(filter.getSortTypeRadioBtn())
            categoryAdapter.setCheckedItem(filter.category)
        }
    }

    private fun observeCategoryList() {
        viewModel.categories.observe(viewLifecycleOwner) { categories ->
            categoryAdapter.setList(categories)
        }
    }

    private fun setupCategoryRecyclerView() {
        categoryAdapter = CategoryAdapter { value ->
            viewModel.setCategory(value)
        }
        binding.recyclerCategory.adapter = categoryAdapter
    }

    private fun FilterData.getItemSizeRadioBtn(): Int = when {
        this.itemsSize == 10 -> R.id.btn10
        this.itemsSize == 20 -> R.id.btn20
        else -> R.id.btn10
    }

    private fun FilterData.getSortTypeRadioBtn(): Int = when {
        this.sort == SortType.ASC -> R.id.btnAsc
        this.sort == SortType.DESC -> R.id.btnDesc
        else -> R.id.btnAsc
    }

    companion object {
        const val FILTER_ARG = "filter_arg"
    }
}