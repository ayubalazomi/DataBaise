package com.example.databaise.di

import android.app.Application
import androidx.room.Room
import com.example.databaise.model.ContactsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModual  {
    @Provides
    @Singleton
    fun provideDatabase(app:Application):ContactsDatabase  {
        val database = Room.databaseBuilder(
            app,ContactsDatabase::class.java,"contact_dp"

        ).build()
        return database


    }



}