package cd.ghost.data

import cd.ghost.data.repositories.ProductsDataRepository
import cd.ghost.data.sources.products.ProductsApi
import cd.ghost.source.products.entities.ResponseProduct
import javax.inject.Inject

class DefProductsDataRepository @Inject constructor(
    private val productsApi: ProductsApi
) : ProductsDataRepository {

    override suspend fun getProductsByCategory(
        category: String?,
        limit: Int?,
        sort: String?
    ): List<ResponseProduct> {
        return productsApi.getProductsByCategory(category, limit, sort)
    }

    override suspend fun getAllProducts(
        limit: Int?,
        sort: String?
    ): List<ResponseProduct> {
        return productsApi.getAllProducts(limit, sort)
    }

    override suspend fun getProduct(productId: Int): ResponseProduct {
        return productsApi.getProduct(productId)
    }

    override suspend fun getCategories(): List<String> {
        return productsApi.getCategories()
    }

}