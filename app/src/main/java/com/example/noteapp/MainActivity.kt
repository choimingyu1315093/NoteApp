package com.example.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noteapp.data.NoteData
import com.example.noteapp.model.Note
import com.example.noteapp.screens.NoteScreen
import com.example.noteapp.screens.NoteViewModel
import com.example.noteapp.ui.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

//Hilt가 MainActivity에 의존성을 주입할 수 있도록 한다.
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteApp { paddingValues, _ ->
                val noteViewModel: NoteViewModel by viewModels()

                NoteScreen(
                    notes = noteViewModel.noteList.collectAsState().value,
                    onAddNote = {
                        noteViewModel.addNote(it)
                    },
                    onRemoveNote = {
                        noteViewModel.deleteNote(it)
                    }
                )
            }
        }
    }
}

@Composable
fun NoteApp(
    noteViewModel: NoteViewModel = viewModel(),
    content: @Composable (PaddingValues, NoteViewModel) -> Unit
){
    NoteAppTheme {
        Scaffold (
            modifier = Modifier
                .statusBarsPadding()
                .safeDrawingPadding()
        ){ innerPadding ->
            content(innerPadding, noteViewModel)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NoteAppPreview() {
    NoteApp { paddingValues, noteViewModel ->
        NoteScreen(
            notes = NoteData().loadNotes(),
            onAddNote = {},
            onRemoveNote = {}
        )
    }
}