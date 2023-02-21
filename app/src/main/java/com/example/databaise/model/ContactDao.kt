package com.example.databaise.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Insert
   suspend fun addcontact(contact: Contact)

   @Query("select * from contact")
   fun getContact(): Flow <List<Contact>>

   @Delete
 suspend  fun delet(contact: Contact)

   @Update
   suspend fun edit(contact: Contact)



}