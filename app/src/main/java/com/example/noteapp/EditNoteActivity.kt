package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.noteapp.databinding.ActivityEditNoteBinding
import com.example.noteapp.dto.NoteDto
import java.text.SimpleDateFormat

class EditNoteActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = intent.getStringExtra("type")

        if(type.equals("SAVE")) {
            binding.btnSave.text = "저장하기"
        } else {
            binding.btnSave.text = "수정하기"
       }

        /* 제목과 내용 입력 후 저장하기 버튼 클릭했을때 이벤트 처리
        *  제목과 내용을 note객체로 담아서 다시 mainActivity로 보낸다.*/
        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val content = binding.tvContent.text.toString()
            val currentDate = SimpleDateFormat("mm월 dd일").format(System.currentTimeMillis())

            if(type.equals("SAVE")) { //
                if(title.isNotEmpty() && content.isNotEmpty()) {
                    val note = NoteDto(0, title, content, currentDate, false)
                    val intent = Intent().apply {
                        putExtra("note", note)
                        putExtra("flag", 0) /* 0이면 save, 1이면 수정 */
                    }
                    setResult(RESULT_OK, intent)
                    finish()
                }
            } else {
                // note 수정
            }
        }
    }
}