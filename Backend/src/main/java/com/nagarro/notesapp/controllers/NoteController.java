package com.nagarro.notesapp.controllers;

import com.nagarro.notesapp.impl.NotesServiceImpl;
import com.nagarro.notesapp.models.Note;
import com.nagarro.notesapp.models.Status;
import com.nagarro.notesapp.models.User;
import com.nagarro.notesapp.repository.NoteRepository;
import com.nagarro.notesapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("*")
public class NoteController {

    @Autowired
    private NotesServiceImpl notesServiceimpl;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteRepository noteRepository;

    @DeleteMapping("/{noteId}/delete")
    public Status toDeleteNote(@PathVariable Long noteId) {
        notesServiceimpl.deleteNoteById(noteId);
        return Status.OK;
    }

    @GetMapping("/{userId}/showNotes")
    public List<Note> getAllNotesForUser(@PathVariable Long userId) {
        return notesServiceimpl.getAllNotesByuser(userId);
    }

    @PostMapping("/{userId}/addNote")
    public Status addNote(@PathVariable Long userId, @RequestBody Note note) throws Exception {

        User user = userRepository.findUserById(userId);
        note.setUser(user);
        return notesServiceimpl.saveNote(note);

    }

    @PutMapping("/{noteId}/editNote")
    public Status updateNote(@PathVariable Long noteId, @RequestBody Note noteDetails) throws Exception {
        Note note = noteRepository.findById(noteId).orElseThrow(() -> new Exception("Note not found"));

        note.setTitle(noteDetails.getTitle());
        note.setDescription(noteDetails.getDescription());
        note.setLastEdit(LocalDateTime.now());

        noteRepository.save(note);
        return Status.SUCCESS;
    }
}
