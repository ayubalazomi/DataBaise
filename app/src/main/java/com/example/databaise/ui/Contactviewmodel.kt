package com.example.databaise.ui

import android.provider.ContactsContract.Contacts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.databaise.model.Contact
import com.example.databaise.model.ContactsDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Contactviewmodel @Inject constructor(
    private val db:ContactsDatabase
):ViewModel() {

    var contacts by mutableStateOf(emptyList<Contact>())
    private set

init {
getContact()
}
    fun getContact(){
        db.dao.getContact().onEach { contactList ->
            contacts=contactList
        }.launchIn(viewModelScope)

    }
}