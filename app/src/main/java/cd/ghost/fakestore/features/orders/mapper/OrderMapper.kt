package cd.ghost.fakestore.features.orders.mapper

import cd.ghost.common.data_formatter.DateFormatter
import cd.ghost.orders.domain.entity.OrderEntity
import cd.ghost.orders.domain.entity.OrderStatus
import cd.ghost.orders.domain.entity.ProductEntity
import cd.ghost.source.carts.entity.CartItemSourceEntity
import cd.ghost.source.orders.entity.OrderProductSourceEntity
import cd.ghost.source.orders.entity.OrderSourceEntity
import javax.inject.Inject

class OrderMapper @Inject constructor(
    private val dateFormatter: DateFormatter
) {

    fun toOrderProductSourceEntity(order: CartItemSourceEntity): OrderProductSourceEntity {
//        return OrderProductSourceEntity(
//        )
        throw Exception()
    }


    fun toOrderEntity(order: OrderSourceEntity): OrderEntity {
        return OrderEntity(
            orderId = order.orderId,
            firstname = order.firstname,
            lastname = order.lastname,
            status = if (order.cancelDate == null) OrderStatus.CREATED else OrderStatus.CANCELED,
            comment = order.comment,
            address = order.address,
            products = order.itemsList.map {
                ProductEntity(
                    productId = it.productId,
                    quantity = it.quantity,
                    thump = it.thump,
                    title = it.title,
                    description = it.description,
                    price = it.price
                )
            },
            createdDate = dateFormatter.format(order.createdDate),
            cancelDate = dateFormatter.format(order.cancelDate)
        )
    }

    private fun toOrderProductSourceEntity(
        product: ProductEntity
    ): OrderProductSourceEntity {
        return OrderProductSourceEntity(
            productId = product.productId,
            quantity = product.quantity,
            title = product.title,
            description = product.description,
            thump = product.thump,
            price = product.price,
        )
    }
}