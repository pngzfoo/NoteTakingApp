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
Parcelable ถูกเพิ่มเข้ามาเพื่อใช้งานในแอนดรอยด์ มีหน้าที่คล้ายๆกับ Serializable(มีจุดประสงค์เพื่อใช้ในการแปลง Model Class ให้อยู่ในรูปของ
Byte Stream เพื่อให้สามารถรับ/ส่งข้อมูลระหว่างอุปกรณ์ได้ โดยมีเงื่อนไขว่า Model Class นั้นๆต้องเก็บข้อมูลข้างในเป็น
Primitive Data Type ทั้งหมด) นั่นแหละ แต่บนแอนดรอยด์ใช้ใน Inter-process communication (IPC)
ซึ่งจะคุ้นเคยกันดีเมื่อจะส่งข้อมูลที่เป็น Model Class จาก Activity ตัวหนึ่งไปให้อีกตัวหนึ่งจะต้องทำเป็น Parcelable ทุกครั้ง
 */