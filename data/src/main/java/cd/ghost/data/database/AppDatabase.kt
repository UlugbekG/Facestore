package cd.ghost.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cd.ghost.data.database.favorites.entity.ProductDataEntity
import cd.ghost.data.database.favorites.FavoritesDao

@Database(
    entities = [ProductDataEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
}