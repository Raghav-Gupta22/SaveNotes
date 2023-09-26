package com.nagarro.notesapp.services;

import com.nagarro.notesapp.models.Note;
import com.nagarro.notesapp.models.Status;

import java.util.List;

public interface NoteService {

    Status saveNote(Note note);

    Status deleteNoteById(Long noteId);

    List<Note> getAllNotesByuser(Long userId);


}
