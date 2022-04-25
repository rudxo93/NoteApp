package com.example.noteapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.dto.NoteDto
import java.lang.IllegalStateException

private const val DATABASE_NAME = "note-database.db"

class NoteRepository private constructor(context: Context) {

    /* dataBase 빌드 */
    private val database: NoteDatabase = Room.databaseBuilder(
        context.applicationContext,
        NoteDatabase::class.java,
        DATABASE_NAME
    ).build()

    /* Dao */
    private val noteDao = database.noteDao()

    /* CRUD */
    fun list(): LiveData<MutableList<NoteDto>> = noteDao.list()

    fun getNote(id: Long): NoteDto = noteDao.selectOne(id)

    fun insert(dto: NoteDto) = noteDao.insert(dto)

    suspend fun update(dto: NoteDto) = noteDao.update(dto)

    fun delete(dto: NoteDto) = noteDao.delete(dto)

    /* 클래스가 생성될 때 메모리에 적재되면서 동시에 생성되는 객체, 데이터베이스 생성 및 초기화 담당 */
    companion object {
        private var INSTANCE: NoteRepository? = null

        fun initialize(context: Context) {
            if(INSTANCE == null){
                INSTANCE = NoteRepository(context)
            }
        }

        fun get(): NoteRepository {
            return INSTANCE ?:
            throw IllegalStateException("NoteRepository must be initialized")
        }
    }
}