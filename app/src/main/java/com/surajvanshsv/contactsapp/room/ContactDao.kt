package com.surajvanshsv.contactsapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact)


    @Query("select * from Contact")
    fun getAllContacts() : LiveData<List<Contact>>

    @Delete
    suspend fun deleteContact(contact: Contact)
}