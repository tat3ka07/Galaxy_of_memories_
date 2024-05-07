package com.example.galaxyofmemories;

import com.example.galaxyofmemories.entities.Note;

public interface NotesListener {
    void onNoteClicked(Note note, int position);
}
