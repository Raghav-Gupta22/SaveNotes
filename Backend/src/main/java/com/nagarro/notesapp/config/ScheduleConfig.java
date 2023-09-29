package com.nagarro.notesapp.config;

import com.nagarro.notesapp.models.Note;
import com.nagarro.notesapp.models.User;
import com.nagarro.notesapp.repository.NoteRepository;
import com.nagarro.notesapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
public class ScheduleConfig {

    final int maxNotes = 10;
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

//    @Scheduled(fixedRate = 60000) //for minute
    @Scheduled(fixedRate = 3600000) //for hour
    public void cleanupOldNotes() {
        List<User> usersWithExcessNotes = getUsersWithExcessNotes();
        usersWithExcessNotes.forEach(this::deleteOldNotes);
    }

    private List<User> getUsersWithExcessNotes() {
        return userRepository.findUsersWithExcessNotes((long)maxNotes);
    }

    private void deleteOldNotes(User user) {
        List<Note> notes = noteRepository.findNotesByUserOrderBylastEditDesc(user.getId());
        for (int i = maxNotes; i < notes.size(); i++) {
            Note noteToDelete = notes.get(i);
            noteRepository.delete(noteToDelete);
        }
    }
}
