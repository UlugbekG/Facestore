package cd.ghost.data.database.favorites.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductDataEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val title: String?,
    val price: String?,
    val description: String?,
    val category: String?,
    val imageUrl: String?,
    val rate: String?,
    val count: String?
)