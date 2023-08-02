package cd.ghost.data

import cd.ghost.common.NoConnectionException
import cd.ghost.common.ParseBackendException
import cd.ghost.data.sources.products.ProductsApi
import cd.ghost.data.sources.products.entities.ResponseProduct
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DataProductsRepositoryImpl @Inject constructor(
    private val productsApi: ProductsApi
) : DataProductsRepository {

    override suspend fun getAllProducts(
        category: String?,
        limit: Int?,
        sort: String?
    ): List<ResponseProduct> = try {
        val allProducts = productsApi.getAllProducts(category, limit, sort)
        allProducts
    } catch (e: HttpException) {
        throw ParseBackendException(e)
    } catch (e: IOException) {
        throw NoConnectionException()
    } catch (e: Exception) {
        throw e
    }

    override suspend fun getProduct(): ResponseProduct {
        TODO("Not yet implemented")
    }

    override suspend fun getCategory(): List<String> {
        TODO("Not yet implemented")
    }

}