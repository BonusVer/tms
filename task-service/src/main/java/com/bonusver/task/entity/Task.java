package com.bonusver.task.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "task_service", name = "t_task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "c_title")
    @NotNull
    @Size(min = 3, max = 50)
    private String title;

    @Column(name = "c_details")
    @NotNull
    @Size(max = 2000)
    private String details;

    @Column(name = "c_priority")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = "c_status")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_id_author")
    private User author;

    @Column(name = "c_id_author", insertable = false, updatable = false)
    private Integer idAuthor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_id_executor")
    private User executor;

    @Column(name = "c_id_executor", insertable = false, updatable = false)
    private Integer idExecutor;

    public enum Status {
        WAITING,
        IN_PROGRESS,
        COMPLETED
    }

    public enum Priority {
        HIGH,
        MEDIUM,
        LOW
    }
    
}
