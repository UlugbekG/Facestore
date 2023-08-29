package cd.ghost.data.repositories

import cd.ghost.common.Container
import cd.ghost.common.wrap
import cd.ghost.source.products.ProductsSource
import cd.ghost.source.products.entities.ResponseProduct
import javax.inject.Inject

class DefProductsDataRepository @Inject constructor(
    private val productsSource: ProductsSource
) : ProductsDataRepository {

    override suspend fun getProductsByCategory(
        category: String?,
        limit: Int?,
        sort: String?
    ): Container<List<ResponseProduct>> = wrap {
        productsSource.getProductsByCategory(category, limit, sort)
    }

    override suspend fun getAllProducts(
        limit: Int?,
        sort: String?
    ): Container<List<ResponseProduct>> = wrap {
        productsSource.getAllProducts(limit, sort)
    }

    override suspend fun getProduct(productId: Int): ResponseProduct {
        return productsSource.getProduct(productId)
    }

    override suspend fun getCategories(): Container<List<String>> = wrap {
        productsSource.getCategories()
    }

}