package cd.ghost.catalog.domain.repos

interface FilterRepository {

    suspend fun getCategories(): List<String>

}