package cd.ghost.data.database.favorites

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cd.ghost.data.database.favorites.entity.ProductDataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductDataEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(products: List<ProductDataEntity>)

    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getProduct(id: Int): ProductDataEntity

    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<ProductDataEntity>>

    @Query("DELETE FROM products where id = :id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM products")
    suspend fun deleteAll()

}