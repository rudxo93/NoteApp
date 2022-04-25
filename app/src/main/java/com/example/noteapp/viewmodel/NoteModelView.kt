package com.example.noteapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.dto.NoteDto
import com.example.noteapp.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/* viewModel 엑티비티의 라이프사이클과 별개로 돌아가기 때문에 데이터의 유지 및 공유 가능
*  viewModel에서 CRUD를 사용해 엑티비티의 이동이 있어도 동일하게 값을 불러올 수 있다.*/
class NoteModelView: ViewModel() {

    val noteList: LiveData<MutableList<NoteDto>>
    private val noteRepository: NoteRepository = NoteRepository.get()

    init {
        noteList = noteRepository.list()
    }

    /* CRUD */
    fun getOne(id: Long) = noteRepository.getNote(id)

    fun insert(dto: NoteDto) = noteRepository.insert(dto)

    fun update(dto: NoteDto) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.update(dto)
    }

    fun delete(dto: NoteDto) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.delete(dto)
    }

}