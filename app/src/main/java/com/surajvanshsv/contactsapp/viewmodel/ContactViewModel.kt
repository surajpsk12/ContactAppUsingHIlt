package com.surajvanshsv.contactsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surajvanshsv.contactsapp.repo.ContactRepository
import com.surajvanshsv.contactsapp.room.Contact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ContactViewModel @Inject constructor(private val repository: ContactRepository) : ViewModel(){

    val allContacts : LiveData<List<Contact>> = repository.allContact

    fun insert(contact: Contact){
        viewModelScope.launch {
            repository.insert(contact)
        }
    }

    fun delete(contact: Contact){
        viewModelScope.launch {
            repository.delete(contact)
        }
    }


}