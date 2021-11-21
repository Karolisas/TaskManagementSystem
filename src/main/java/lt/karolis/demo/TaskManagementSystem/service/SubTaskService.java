package lt.karolis.demo.TaskManagementSystem.service;

import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import lt.karolis.demo.TaskManagementSystem.controller.TaskNotFoundException;
import lt.karolis.demo.TaskManagementSystem.persistance.SubTask;
import lt.karolis.demo.TaskManagementSystem.persistance.SubTaskRepository;
import lt.karolis.demo.TaskManagementSystem.persistance.Task;
import lt.karolis.demo.TaskManagementSystem.persistance.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubTaskService {

    @Autowired
    SubTaskRepository repository;

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
