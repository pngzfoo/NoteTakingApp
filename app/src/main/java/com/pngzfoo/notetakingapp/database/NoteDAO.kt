package com.pngzfoo.notetakingapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pngzfoo.notetakingapp.model.Note

@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM NOTES ORDER BY id DESC")
    fun getAllNotes():LiveData<List<Note>>

    @Query("SELECT * FROM NOTES WHERE noteTitle LIKE : query OR noteBody LIKE : query")
    fun searchNote(query:String?):LiveData<List<Note>>
}
/*
ตรงนี้คือการสร้างฟังก์ชั่น ขึ้นมา Reference คอมมานด์ หรือ ที่เราเรียกว่า Queries ต่าง ๆ ใน Relational Database นั่นเอง
กล่าวคือ Data Access Object จะช่วยให้เราไม่ต้องเขียน Query ยาว ๆ เพียงแต่เรียกเป็นฟังก์ชันสั้น ๆ แค่นั้นเอง
โดย Data Access Object จะสร้างอยู่ในรูปแบบของ Interface

ถ้าตามหลักของ OOP Interface คือ โครงสร้างของ Class อันนี้ก็เช่นเดียวกัน คือ มีความสามารถ มี Method อะไรบ้าง
Return ค่ามั้ย ถ้า Return จะกลับมาเป็นอะไร รับค่ามั้ย เราจะให้พารามิเตอร์มันชื่ออะไร เป็นลักษณะแบบไหน Int, Float, Char หรือ เป็น User Define Type
อย่างที่เราทำ Class, Data Class เราใส่ไปให้หมด โดยไม่ต้อง Implement Method (ที่ Kotlin เรียก fun ) ข้างใน
 */