package lt.karolis.demo.TaskManagementSystem.persistance;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TASKS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

//    SELECT * FROM SUB_TASKS ;
//    SELECT * FROM Tasks;

    //    @OneToMany(mappedBy = "parentTask", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(mappedBy = "parentTask", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    @JoinColumn(name = "XX_SUB_TASK_ID")
//    @OneToMany(cascade = CascadeType.ALL)
    private List<SubTask> subTask;

    public List<SubTask> getSubTask() {
        return subTask;
    }

    public Task setSubTask(List<SubTask> subTask) {
        this.subTask = subTask;
        return this;
    }

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
