package cd.ghost.catalog.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cd.ghost.catalog.databinding.ItemProductBinding
import cd.ghost.catalog.domain.entity.EntityProduct

interface OnClickListener {
    fun onClick(item: EntityProduct)
    fun onLongClick(item: EntityProduct)
}

class ProductsAdapter constructor(
    private val onClick: OnClickListener
) : ListAdapter<EntityProduct, ProductsAdapter.ProductViewHolder>(DifferUtil()) {

    inner class ProductViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: EntityProduct) {
            binding.apply {
                tvProduct.text = item.title
            }
            itemView.setOnClickListener {
                onClick.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            binding = ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}

class DifferUtil : DiffUtil.ItemCallback<EntityProduct>() {
    override fun areItemsTheSame(oldItem: EntityProduct, newItem: EntityProduct): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: EntityProduct, newItem: EntityProduct): Boolean {
        return oldItem.id == newItem.id
    }
}