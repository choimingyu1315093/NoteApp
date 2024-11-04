package com.example.noteapp.di

import android.content.Context
import androidx.room.Room
import com.example.noteapp.data.NoteDatabase
import com.example.noteapp.data.NoteDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//앱이 실행되기 전에 만들어지는게 아니라, 필요한 의존성이 요청될 때 Hilt에 의해서 한 번 생성되고, 이후에는 재사용된다.
@InstallIn(SingletonComponent::class) //AppModule에서 제공하는 의존성이 애플리케이션의 생명 주기 동안 유지되는 SingletonComponent에 설치됨을 나타냄.
@Module //AppModule이 Hilt에 의존성을 제공하는 모듈임을 나타냄.
object AppModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(context, NoteDatabase::class.java, "notes_db")
            .fallbackToDestructiveMigration() //데이터베이스의 버전이 변경되었을 때, 기존 데이터를 삭제하고 새로운 데이터베이스를 만드는 기능을 제공.
            .build()

    @Singleton
    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDatabaseDao = noteDatabase.noteDao()
}