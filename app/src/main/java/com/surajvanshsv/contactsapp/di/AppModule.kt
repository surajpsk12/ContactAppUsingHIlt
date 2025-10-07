package com.surajvanshsv.contactsapp.di

import android.content.Context
import com.surajvanshsv.contactsapp.room.ContactDao
import com.surajvanshsv.contactsapp.room.ContactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : ContactDatabase {
        return ContactDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideContactDao(contactDatabase: ContactDatabase) : ContactDao {
        return contactDatabase.contactDao()
    }
}