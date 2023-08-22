package cd.ghost.catalog.presentation.productlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cd.ghost.catalog.R
import cd.ghost.catalog.databinding.ItemProductBinding
import cd.ghost.catalog.domain.entity.ProductEntity
import coil.load
import coil.transform.CircleCropTransformation

interface OnClickListener {
    fun onClick(item: ProductEntity)
    fun onLongClick(item: ProductEntity)
}

class ProductsAdapter constructor(
    private val onClick: OnClickListener
) : ListAdapter<ProductEntity, ProductsAdapter.ProductViewHolder>(DifferUtil()) {

    inner class ProductViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ProductEntity) {
            binding.apply {
                ivProduct.load(item.imageUrl) {
                    crossfade(true)
                    placeholder(R.drawable.image_place_holder)
                    transformations(CircleCropTransformation())
                }
                tvTitle.text = item.title
                tvPrice.text = "$${item.price}"
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

class DifferUtil : DiffUtil.ItemCallback<ProductEntity>() {
    override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
        return oldItem.id == newItem.id
    }
}