package cd.ghost.catalog.presentation.filter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cd.ghost.catalog.R
import cd.ghost.catalog.databinding.ItemCategoryBinding
import cd.ghost.catalog.domain.entity.Category
import cd.ghost.catalog.domain.entity.getCategoryBy

class CategoryAdapter(
    private val onClick: (Category) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val list = ArrayList<String?>()
    private var selectedCategory: Category = Category.ALL

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newList: List<String?>) {
        list.addAll(newList)
        notifyDataSetChanged()
    }

    fun setCheckedItem(category: Category) {
        if (selectedCategory == category) return
        selectedCategory = category
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: String?) {
            binding.apply {
                root.text = item
                if (item == selectedCategory.value) {
                    root.setBackgroundResource(R.drawable.category_back)
                    root.setTextColor(root.context.getColor(cd.ghost.presentation.R.color.white))
                } else {
                    root.setBackgroundResource(R.drawable.normal_back)
                    root.setTextColor(root.context.getColor(cd.ghost.presentation.R.color.dark_gray))
                }
            }

            itemView.setOnClickListener {
                onClick(item?.getCategoryBy() ?: Category.ALL)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }
}