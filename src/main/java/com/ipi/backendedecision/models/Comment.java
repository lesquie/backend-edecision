package com.ipi.backendedecision.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private int id;

    private String content;

    private LocalDateTime creationDate;

    @ManyToOne
    private User owner;

    public Comment(String content, User owner) {
        this.content = content;
        this.creationDate = LocalDateTime.now();
        this.owner = owner;
    }
}
