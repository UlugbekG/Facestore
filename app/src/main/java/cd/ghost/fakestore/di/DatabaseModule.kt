package cd.ghost.fakestore.di

import android.content.Context
import androidx.room.Room
import cd.ghost.data.database.AppDatabase
import cd.ghost.data.database.favorites.FavoritesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "FACESTORE_APP_DB")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideFavoritesDao(
        appDatabase: AppDatabase
    ): FavoritesDao {
        return appDatabase.favoritesDao()
    }

}