package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.dto.NoteDto
import com.example.noteapp.viewmodel.NoteModelView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var noteViewModel: NoteModelView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteViewModel = ViewModelProvider(this)[NoteModelView::class.java]

        /* intent를 통해 this로 부터 EditNoteActivity로 이동시킨다.
        *  이때 apply를 통해 함께 보낼 데이터를 key:value 형태로 담아준다.
        *  EditNoteActivity는 추가와 수정을 동시에 담당
        *  그렇기 때문에 추가할지 수정할지 구분해야한다.*/
         binding.addNote.setOnClickListener {
             val intent = Intent(this, EditNoteActivity::class.java).apply{
                 putExtra("type", "SAVE")
             }
             requestActivity.launch(intent)
         }

    }

    private val requestActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        /* EditNoteActivity에서 담은 데이트들은 it.data에 담겨지기 때문에 다시 값을 받아 저장해야한다.
        *  todoViewModel.insert(note)를 통해 -> viewModel -> noteRepository -> noteDao 순으로 타고 들어가서 데이터베이스에 저장한다.*/
        if(it.resultCode == RESULT_OK) {
            val note = it.data?.getSerializableExtra("note") as NoteDto
            when(it.data?.getIntExtra("flag", -1)) {
                0 -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        noteViewModel.insert(note)
                    }
                    Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}