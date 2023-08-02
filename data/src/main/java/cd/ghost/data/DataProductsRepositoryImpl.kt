package cd.ghost.data

import cd.ghost.data.sources.products.ProductsApi
import cd.ghost.data.sources.products.entities.ResponseProduct
import javax.inject.Inject

class DataProductsRepositoryImpl @Inject constructor(
    private val productsApi: ProductsApi
) : DataProductsRepository, BaseRepository() {
    override suspend fun getProductsByCategory(
        category: String?,
        limit: Int?,
        sort: String?
    ): List<ResponseProduct> = catchingBlock {
        productsApi.getProductsByCategory(category, limit, sort)
    }

    override suspend fun getAllProducts(
        limit: Int?,
        sort: String?
    ): List<ResponseProduct> = catchingBlock {
        productsApi.getAllProducts(limit, sort)
    }

    override suspend fun getProduct(productId: Int): ResponseProduct = catchingBlock {
        productsApi.getProduct(productId)
    }

    override suspend fun getCategory(): List<String> = catchingBlock {
        productsApi.getCategories()
    }

}