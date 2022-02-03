package lt.karolis.demo.TaskManagementSystem.service;

import lt.karolis.demo.TaskManagementSystem.exception.TaskNotFoundException;
import lt.karolis.demo.TaskManagementSystem.persistance.domain.SubTask;
import lt.karolis.demo.TaskManagementSystem.persistance.repository.SubTaskRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubTaskService {

    private final SubTaskRepository repository;

    public SubTaskService(SubTaskRepository repository) {
        this.repository = repository;
    }

    public SubTask getSubTaskById(Long id) {
        System.out.println("getTaskById " + id);
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("No task Found with id: " + id));
    }

    public List<SubTask> getAllTasks() {
        return repository.findAll();
    }

    public SubTask createTask(SubTask task) {
        return repository.save(task);
    }


    public void deleteTask(Long id) throws EmptyResultDataAccessException {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new TaskNotFoundException("Tokio įrašo nėra, jau ištrintas?");
        }
    }

    public SubTask updateTask(Long id, SubTask task) {
        repository.findById(id)
                .map(a -> {
                            a.setDescription(task.getDescription());
                            a.setLevelPriority(task.getLevelPriority());
                            a.setTitle(task.getTitle());
                            return repository.save(a);
                        }
                ).orElseThrow(() -> new TaskNotFoundException("No task to update"));

        return repository.findById(id).get();
    }

    public List<SubTask> getSubtasksByParent(Long id){
        return repository.findAllByParentTask(id);
    }
}
