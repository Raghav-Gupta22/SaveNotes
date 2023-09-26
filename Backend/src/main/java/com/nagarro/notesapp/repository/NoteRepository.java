package com.nagarro.notesapp.repository;

import com.nagarro.notesapp.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
//    @Query(value = "from Notes n")
//    List<Notes> findNotesById();

    @Query("SELECT n FROM Note n WHERE n.user.id = :id")
    List<Note> findAllNotesByUserId(Long id);

    @Query("SELECT n FROM Note n WHERE n.user.id = :id ORDER BY n.lastEdit DESC")
    List<Note> findNotesByUserOrderBylastEditDesc(Long id);

//    @Override
//    void deleteById(Long id);
}
