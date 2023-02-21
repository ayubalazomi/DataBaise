package com.example.databaise.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.databaise.model.Contact
import com.example.databaise.model.ContactsDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddContactviewmodel @Inject constructor (

    private val db:ContactsDatabase
        ):ViewModel() {

            fun addContact(titeltext: String, contenttext: String) {
                viewModelScope.launch {
                    val contact=Contact(titel = titeltext, inputnotes  = contenttext)
                    db.dao.addcontact(contact)
                }

            }

    fun deleet (del:Contact){
        viewModelScope.launch {
            db.dao.delet(del)
        }

    }

    fun edit (ed:Contact){
        viewModelScope.launch {
            db.dao.edit(ed)
        }
    }


}