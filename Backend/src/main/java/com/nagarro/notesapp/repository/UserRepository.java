package com.nagarro.notesapp.repository;

import com.nagarro.notesapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmailAndPassword(String email, String password);

    @Query("select u from User u where u.email = :email")
    public User getUserByEmail(@Param("email") String email);
//
    @Query("select u from User u where u.id = :userId")
    public User findUserById(@Param("userId") Long userId);

    @Query("SELECT u FROM User u JOIN u.notes n GROUP BY u HAVING COUNT(n) > :maxNotes")
    List<User> findUsersWithExcessNotes(@Param("maxNotes") Long maxNotes);
}