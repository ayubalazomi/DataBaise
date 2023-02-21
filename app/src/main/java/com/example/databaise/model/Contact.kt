package com.example.databaise.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id :Int=0,

    var titel:String="",

    var inputnotes:String=""
): Parcelable
