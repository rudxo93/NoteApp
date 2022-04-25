package com.example.noteapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.noteapp.dto.NoteDto

@Dao
interface NoteDao {

    /* 충돌이 발생할 경우 덮어쓰기 */
    @Insert(onConflict = REPLACE)
    fun insert(dto: NoteDto)

    /* 전체 리스트 조회
    *  LiveData를 사용해서 추가, 수정, 삭제에 의해 변화하는 값에 대해 즉시 반영 가능하도록 */
    @Query("select * from noteTable")
    fun list(): LiveData<MutableList<NoteDto>>

    /* 하나의 글 조회 */
    @Query("select * from noteTable where id = (:id)")
    fun selectOne(id: Long): NoteDto

    @Update
    suspend fun update(dto: NoteDto)

    @Delete
    fun delete(dto: NoteDto)
}