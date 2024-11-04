package com.example.noteapp.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.R
import com.example.noteapp.components.NoteButton
import com.example.noteapp.components.NoteInputText
import com.example.noteapp.model.Note
import com.example.noteapp.util.formatDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit,
    modifier: Modifier = Modifier
){
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name)
                    )
                },
                actions = {
                    Icon(
                        imageVector = Icons.Rounded.Notifications,
                        contentDescription = "Notification Icon"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(Color(0xFFDADFE3))
            )
        },
        modifier = modifier.padding(6.dp)
    ){ innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NoteInputText(
                text = title,
                label = "Title",
                onTextChange = {
                    if(it.all { char ->
                        //isLetter() 문자(한글, 영어 등) 인지
                        //isWhitespace space 클릭 했는지
                        char.isLetter() || char.isWhitespace()
                        }) title = it
                },
                modifier = modifier.padding(top = 9.dp, bottom = 8.dp)
            )

            NoteInputText(
                text = description,
                label = "Add a note",
                onTextChange = {
                    if(it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
                },
                modifier = modifier.padding(top = 9.dp, bottom = 8.dp)
            )

            NoteButton(
                text = "Save",
                onClick = {
                    if(title.isNotEmpty() && description.isNotEmpty()){
                        onAddNote(Note(title = title, description = description))
                        title = ""
                        description = ""
                        Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                    }
                }
            )
            Spacer(
                modifier = modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.LightGray)
            )
            LazyColumn (
                modifier = modifier.fillMaxWidth()
            ){
                items(notes){note ->
                    NoteRow(
                        note = note,
                        onNoteClicked = {
                            onRemoveNote(note)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun NoteRow(
    note: Note,
    onNoteClicked: (Note) -> Unit,
    modifier: Modifier = Modifier
){
    Surface (
        modifier = modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        shadowElevation = 6.dp
    ){
        Column (
            modifier = modifier
                .clickable {
                    onNoteClicked(note)
                }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start
        ){
            Text(
                text = note.title,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = note.description,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = formatDate(note.entryDate.time),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}