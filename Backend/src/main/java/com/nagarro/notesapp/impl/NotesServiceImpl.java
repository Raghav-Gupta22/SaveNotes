package com.nagarro.notesapp.impl;

import com.nagarro.notesapp.models.Note;
import com.nagarro.notesapp.models.Status;
import com.nagarro.notesapp.repository.NoteRepository;
import com.nagarro.notesapp.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Status saveNote(Note note) {
        noteRepository.save(note);
        return Status.SUCCESS;
    }

    @Override
    public Status deleteNoteById(Long noteId) {
        noteRepository.deleteById(noteId);
        return Status.SUCCESS;
    }

    @Override
    public List<Note> getAllNotesByuser(Long userId) {
        return noteRepository.findAllNotesByUserId(userId);
    }
}
