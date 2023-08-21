package cd.ghost.source

import cd.ghost.data.sources.products.ProductsApi
import cd.ghost.source.products.ProductsSource
import cd.ghost.source.products.entities.ResponseProduct
import javax.inject.Inject

class DefProductsSource @Inject constructor(
    private val productsApi: ProductsApi
) : BaseSource(), ProductsSource {

    override suspend fun getProductsByCategory(
        category: String?,
        limit: Int?,
        sort: String?
    ): List<ResponseProduct> = catchExceptions {
        productsApi.getProductsByCategory(category, limit, sort)
    }

    override suspend fun getAllProducts(limit: Int?, sort: String?): List<ResponseProduct> =
        catchExceptions {
            productsApi.getAllProducts(limit, sort)
        }

    override suspend fun getProduct(productId: Int): ResponseProduct = catchExceptions {
        productsApi.getProduct(productId)
    }

    override suspend fun getCategories(): List<String> = catchExceptions {
        productsApi.getCategories()
    }
}