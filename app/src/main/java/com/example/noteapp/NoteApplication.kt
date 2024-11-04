package com.example.noteapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Hilt를 사용하는 앱이라고 알려주는 역할.
@HiltAndroidApp
class NoteApplication: Application() {

}