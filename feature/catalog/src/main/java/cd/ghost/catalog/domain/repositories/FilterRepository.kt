package cd.ghost.catalog.domain.repositories

interface FilterRepository {

    suspend fun getCategories(): List<String>

}