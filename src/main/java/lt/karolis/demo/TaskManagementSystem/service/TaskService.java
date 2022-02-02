package lt.karolis.demo.TaskManagementSystem.service;

import lt.karolis.demo.TaskManagementSystem.controller.TaskNotFoundException;
import lt.karolis.demo.TaskManagementSystem.persistance.Priority;
import lt.karolis.demo.TaskManagementSystem.persistance.SubTask;
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

//    @Autowired
//    SubTaskService subTaskService;

    public Task getTaskById(Long id) throws TaskNotFoundException {
        System.out.println("getTaskById " + id);
        return repository.findById(id).orElse(null);
//                .orElseThrow(() -> new TaskNotFoundException("No task Found with id: " + id));
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task createTask(Task task) {
        Task newTask = null;
        if (task.getDescription() != null){
            return repository.save(task);
        } else {
            return newTask;
        }
//        return repository.save(task);
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

    public Task changeTask(Long id, Priority priority) {
      Task newTask =   repository.findById(id)
                .map(task -> {
                            if (!areSubtasksNotDone(task)){
                                task.setLevelPriority(priority);
                            }
                            return repository.save(task);
                        }
                ).orElseThrow(() -> new TaskNotFoundException("Task has unfinished subTasks"));

        return repository.save(newTask);
    }

    public boolean areSubtasksNotDone(Task task) {
        return task.getSubTasks()
                .stream()
                .map(SubTask::getLevelPriority)
                .anyMatch(priority -> !Priority.DONE.equals(priority));
    }

    public Task closeTask(Long id) {
        return changeTask(id, Priority.DONE);
    }
}
