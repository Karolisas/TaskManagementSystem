package lt.karolis.demo.TaskManagementSystem.persistance;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
@Table(name = "TASKS")
public class Task {

    @Id
    @GeneratedValue

    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private int levelPriority;


    public Long getId() {
        return id;
    }

    public Task setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Task setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Task setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getLevelPriority() {
        return levelPriority;
    }

    public Task setLevelPriority(int levelPriority) {
        this.levelPriority = levelPriority;
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
