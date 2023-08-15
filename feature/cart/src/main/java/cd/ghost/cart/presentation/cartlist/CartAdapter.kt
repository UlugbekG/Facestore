package cd.ghost.cart.presentation.cartlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cd.ghost.cart.databinding.ItemCartBinding
import cd.ghost.cart.domain.entity.CartItem
import coil.load

interface CartAdapterOnClickListener {
    fun onItemClick(cartItem: UiCartItem)
    fun onIncrementClick(cartItem: UiCartItem)
    fun onDecrementClick(cartItem: UiCartItem)
    fun onToggle(cartItem: UiCartItem)
}

class CartAdapter(
    private val onClickListener: CartAdapterOnClickListener
) : ListAdapter<UiCartItem, CartAdapter.CartViewHolder>(CartDiff()) {

    inner class CartViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(item: UiCartItem) {
            binding.apply {
                tvTitle.text = item.product.title.toString()
                tvTotalPrice.text = "$${item.totalOriginPrice}"
                tvQuantity.text = item.quantity.toString()
                contentImage.load(item.product.imageUrl) {
                    crossfade(true)
                }

                checkbox.isVisible = item.showCheckbox
                checkbox.isChecked = item.isChecked

                btnIncrease.setOnClickListener {
                    onClickListener.onIncrementClick(item)
                }

                btnDecrease.setOnClickListener {
                    onClickListener.onDecrementClick(item)
                }

                itemView.setOnLongClickListener {
                    onClickListener.onToggle(item)
                    true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder =
        CartViewHolder(
            ItemCartBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class CartDiff : DiffUtil.ItemCallback<UiCartItem>() {
        override fun areItemsTheSame(
            oldItem: UiCartItem, newItem: UiCartItem
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: UiCartItem,
            newItem: UiCartItem
        ): Boolean = oldItem == newItem

    }
}