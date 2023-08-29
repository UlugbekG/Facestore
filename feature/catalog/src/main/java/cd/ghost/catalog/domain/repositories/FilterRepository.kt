package cd.ghost.catalog.domain.repositories

import cd.ghost.common.Container

interface FilterRepository {

    suspend fun getCategories(): Container<List<String>>

}