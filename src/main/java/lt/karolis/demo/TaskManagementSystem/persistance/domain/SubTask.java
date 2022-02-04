package lt.karolis.demo.TaskManagementSystem.persistance.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lt.karolis.demo.TaskManagementSystem.persistance.Priority;

import javax.persistence.*;

@Entity
@Table(name = "SUB_TASKS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SubTask {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column(name = "level_priority")
    private Priority levelPriority;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
//    @JsonBackReference
    private Task parentTask;

    public Task getParentTask() {
        return parentTask;
    }

    public SubTask setParentTask(Task parentTask) {
        this.parentTask = parentTask;
        return this;
    }

    public Long getId() {
        return id;
    }

    public SubTask setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SubTask setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SubTask setDescription(String description) {
        this.description = description;
        return this;
    }

    public Priority getLevelPriority() {
        return levelPriority;
    }

    public SubTask setLevelPriority(Priority priority) {
        this.levelPriority = priority;
        return this;
    }


    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", levelPriority=" + levelPriority +
                '}';
    }
}
