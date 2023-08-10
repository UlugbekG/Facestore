package cd.ghost.cart.presentation.cartlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cd.ghost.cart.databinding.ItemCartBinding
import cd.ghost.cart.domain.entity.CartItem
import coil.load

interface CartAdapterOnClickListener {
    fun onItemClick(cartItem: CartItem)
    fun onIncreaseBtnClick(id: Int, quantity: Int)
    fun onDecreaseBtnClick(id: Int, quantity: Int)
    fun onLongClick(cartItem: CartItem)
}

class CartAdapter(
    private val onClickListener: CartAdapterOnClickListener
) : ListAdapter<CartItem, CartAdapter.CartViewHolder>(CartDiff()) {

    inner class CartViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: CartItem) {
            binding.apply {
                tvTitle.text = item.product.title.toString()
                tvQuantity.text = item.quantity.toString()
                contentImage.load(item.product.imageUrl) {
                    crossfade(true)
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

    class CartDiff : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(
            oldItem: CartItem, newItem: CartItem
        ): Boolean = oldItem.productId == newItem.productId

        override fun areContentsTheSame(
            oldItem: CartItem,
            newItem: CartItem
        ): Boolean = oldItem == newItem

    }
}