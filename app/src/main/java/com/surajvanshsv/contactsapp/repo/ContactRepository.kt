package com.surajvanshsv.contactsapp.repo

import androidx.lifecycle.LiveData
import com.surajvanshsv.contactsapp.room.Contact
import com.surajvanshsv.contactsapp.room.ContactDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactRepository @Inject constructor(private val contactDao: ContactDao) {

    val allContact : LiveData<List<Contact>> = contactDao.getAllContacts()

    suspend fun insert(contact: Contact){
        contactDao.insertContact(contact)
    }

    suspend fun delete(contact: Contact){
        contactDao.deleteContact(contact)
    }


}