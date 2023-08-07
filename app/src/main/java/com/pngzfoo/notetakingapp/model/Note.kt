package com.pngzfoo.notetakingapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "notes")
@Parcelize
data class Note(
    @PrimaryKey
    val id : Int,
    val noteTitle : String,
    val noteBody : String

): Parcelable
/*
Parcelable is an interface that a class can implement to be pass with in an intent from an activity
to another one this way transporting data from one activity to another one
by default Parcelable will need to override function that describe content function and write to parcel

 */