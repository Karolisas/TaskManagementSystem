package lt.karolis.demo.TaskManagementSystem.service;

import lt.karolis.demo.TaskManagementSystem.controller.TaskNotFoundException;
import lt.karolis.demo.TaskManagementSystem.persistance.Task;
import lt.karolis.demo.TaskManagementSystem.persistance.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository repository;

    public Task getTaskById(Long id) {
        System.out.println("getTaskById " + id);
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("No task Found with id: " + id));
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task createTask(Task task) {
        return repository.save(task);
    }


    public void deleteTask(Long id) throws EmptyResultDataAccessException {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new TaskNotFoundException("Tokio įrašo nėra, jau ištrintas?");
        }
    }

    public Task updateTask(Long id, Task task) {
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
}
