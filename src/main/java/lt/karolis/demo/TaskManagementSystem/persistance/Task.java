package lt.karolis.demo.TaskManagementSystem.persistance;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
    private Priority levelPriority;

//    SELECT * FROM SUB_TASKS ;
//    SELECT * FROM Tasks;

    //    @OneToMany(mappedBy = "parentTask", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(mappedBy = "parentTask", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    @JoinColumn(name = "XX_SUB_TASK_ID")
//    @OneToMany(cascade = CascadeType.ALL)
    private List<SubTask> subTasks;

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public Task setSubTasks(List<SubTask> subTask) {
        this.subTasks = subTask;
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

    public Priority getLevelPriority() {
        return levelPriority;
    }

    public Task setLevelPriority(Priority levelPriority) {
        this.levelPriority = levelPriority;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != null ? !id.equals(task.id) : task.id != null) return false;
        if (title != null ? !title.equals(task.title) : task.title != null) return false;
        if (description != null ? !description.equals(task.description) : task.description != null) return false;
        if (levelPriority != task.levelPriority) return false;
        return subTasks != null ? subTasks.equals(task.subTasks) : task.subTasks == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (levelPriority != null ? levelPriority.hashCode() : 0);
        result = 31 * result + (subTasks != null ? subTasks.hashCode() : 0);
        return result;
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
