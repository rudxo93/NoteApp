package com.example.noteapp.config

import android.app.Application
import com.example.noteapp.repository.NoteRepository

/* 앱 실행과 동시에 Repository 초기화를 통해 데이터베이스가 없을 경우 새로 빌드 */
class ApplicationClass: Application() {

    override fun onCreate() {
        super.onCreate()

        NoteRepository.initialize(this)
    }
}