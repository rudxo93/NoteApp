package com.example.noteapp.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "noteTable") /* 테이블 이름 */
class NoteDto (
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "noteDate") val noteDate: String
): Serializable {

}

/*
*   note에 필요한 값들 id / title / content / noteDate 가 있어야 한다.
* */