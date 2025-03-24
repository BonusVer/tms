package com.bonusver.task.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "task-service", name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "c_first_name")
    @NotNull
    @Size(max = 50)
    private String firstName;

    @Column(name = "c_last_name")
    @NotNull
    @Size(max = 50)
    private String lastName;

    @Column(name = "c_email")
    @NotNull
    @Email
    private String email;

    @OneToMany(mappedBy = "executor",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)

    private List<Task> tasks;

    @OneToMany(mappedBy = "author",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    private List<User> users;


}
