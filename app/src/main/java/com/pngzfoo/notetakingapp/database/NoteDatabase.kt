package com.pngzfoo.notetakingapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pngzfoo.notetakingapp.model.Note

@Database(entities = [Note::class],version=1)
abstract class NoteDatabase:RoomDatabase() { //เราจะเริ่มสร้าง Database ของเราโดยเริ่มจากสร้าง Class ที่ extend RoomDatabase

    /*
    เอา Data Access Object หรือแหล่งรวม Query ในรูปแบบกำหนดเป็นชื่อฟังก์ชันของเรามาใส่
    โดยเราต้องใส่ในรูปแบบของ Abstract Variable ก็ถ้า Abstract Class
    ไม่มีอะไรเป็น Abstract แล้วเราจะสร้างมันเป็น Abstract ทำไมหละ
     */
    abstract fun getNoteDao() : NoteDAO

    /*
    สร้าง Companion Object มาใช้ในการให้ไฟล์อื่น ๆ สามารถเจ้าถึง Database หรือ จะ Initial Database ได้
    โดยที่ไม่ต้องไปสร้างเป็นตัวอย่าง หรือ เป็น Instance เอง

    สร้างตัวแปรตัวอย่างของเรา หรือ INSTANCE ของเราขึ้นมา อย่างที่บอกไว้คือ ตัวลูกไม่ต้องสร้างเอง เราสร้างไว้ตรงนี้เลย
    โดยตัวแปลของ INSTANCE ของเราจะเป็นชนิด NoteDatabase ก็คือ เรากำลังสร้าง Database ที่มี Table NoteDatabase ที่เราเขียนโครงสร้างของตารางมาแล้ว
    โดยเราจะมีการกำหนดนิดนึงว่า ตัวแปลรัวนี้จะเป็นตัวแปรรูปแบบ @Volatile คือ เราจะไม่ขออยู่ใน Cache ขออยู่ใน Memory ไปเลย
    จะทำให้ตัวแปร INSTANCE หรือตัวแปรแทนโครงสร้างตารางไม่อยู่ใน Cache จะได้ไม่ต้องกลัวปัญหาว่ามันจะตรงกันหรือเปล่า ใน Cache กับ
    ใน RAM เรายอมอาจจะช้าลงนิดนึง แต่ชัวร์

    โดยเราจะสร้างตัวแปรเป็น Nullable และ ตั้งค่าเริ่มต้นเป็น null และเป็นตัวแปรแบบเปลี่ยนค่าได้
     */
    companion object{
        @Volatile
        private var instance : NoteDatabase? = null
        /*
        ที่เราตั้งเป็น Null ก็เพราะว่า ตอนนี้ INSTANCE เทียบได้กับ Database ที่เราจะสร้างแล้ว แต่ว่าเราจะยังไม่สร้าง
        เราจะตรวจสอบก่อนว่าตอนนี้มันมีอยู่แล้วหรือไม่ เพราะเราอาจจะไม่ได้ build หรือ Install / Run App นี้ครั้งเดียว
        ในระหว่างการพัฒนา ดังนั้นเราจะเช็คก่อนว่า Database มีอยู่แล้วหรือไม่ ถ้ามีก็ไม่ต้องสร้างถ้าไม่มีก็สร้าง จะได้ไม่ซับซ้อน
         */

        private val LOCK = Any()
        /*
        Any is a function that is added as an extension to iterable and map interface which take
        a higher order function as param to predicate the condition and return boolean as true
        if any of the items in the list, set or map confirms that condition, otherwise it will return false
         */

        operator fun invoke(context : Context) = instance ?:
        synchronized(LOCK){
            /*Synchronyze is a key word that is used when we deal with multithreading
            and prevent any unsynchronyze data
            Synchronyze Program แล้วดูว่า instance ของเรามีอยู่หรือไม่
            เราจะใช้การ Smart Cast สร้างตัวแปลใน Function
            นี้อีกตัว เป็นการ Copy มาจะได้ไม่มีปัญหาค่าซ้ำซ้อนของตัวแปร แล้วค่อยส่งค่ากลับไป
            */
            instance ?:
            createDatabase(context).also{
                instance = it
            }
        }

        private fun createDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                "note_db"
            ).build()


    }
}

/*
เราจะสร้าง Database โดยการสร้าง Abstract Class ซึ่ง Abstract Class ใน OOP คือ Class ที่ไม่สามารถสร้าง Object ได้โดยตรง
แต่สามารถให้มีการถ่ายทอดไปยัง Class ลูกก่อน แล้ว Class ลูกก็จะ Implement Abstract Method จากนั้น ก็จัดการได้เลย
ใน Abstract Class จะมีอย่างน้อย 1 Abstract Method ที่ว่าง ๆ เลย ให้ Class ลูก ไปจัดการ Implement เอาเอง
และ Method อื่น ๆ เราสามารถเขียนต่าง ๆ ลงใน Method ได้เลย ว่าจะให้ Method นี้ทำงานอะไร
ซึ่งตรงนี้เองที่ทำให้ Abstract Class แตกต่างจาก Interface

 */