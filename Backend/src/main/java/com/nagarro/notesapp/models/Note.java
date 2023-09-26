package com.nagarro.notesapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;

    @Size(max = 500)
    @Pattern(regexp = "^[a-zA-Z0-9@;& *+\\-]*$", message = "Only alphabets, numbers, @, ;, &, *, are allowed")
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastEdit;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @PrePersist
    public void prePersist() {
        if (lastEdit == null) {
            lastEdit = LocalDateTime.now();
        }
    }
}
