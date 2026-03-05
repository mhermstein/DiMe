package com.mhermstein.dime.di

import android.content.Context
import androidx.room.Room
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mhermstein.dime.data.local.AppDatabase
import com.mhermstein.dime.data.local.dao.YourDao
import com.mhermstein.dime.data.repository.YourRepository
import com.mhermstein.dime.ui.viewmodel.YourViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "app_database").build()
    }

    @Provides
    @Singleton
    fun provideYourDao(database: AppDatabase): YourDao {
        return database.yourDao()
    }

    @Provides
    @Singleton
    fun provideYourRepository(yourDao: YourDao): YourRepository {
        return YourRepository(yourDao)
    }

    @Provides
    fun provideYourViewModel(repository: YourRepository): ViewModel {
        return YourViewModel(repository)
    }
}