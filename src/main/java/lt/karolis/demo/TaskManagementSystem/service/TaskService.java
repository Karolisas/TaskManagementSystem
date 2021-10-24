package lt.karolis.demo.TaskManagementSystem.service;

import lt.karolis.demo.TaskManagementSystem.persistance.Task;
import lt.karolis.demo.TaskManagementSystem.persistance.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    TaskRepository repository;

    public String getTaskById(Long id) {
        System.out.println("getTaskById " + id);
        Task task2 = new Task().setDescription("Aprasymas").setId(1L).setLevelPriority(6).setTitle("Pavadinimas");

        return "TaslServoce id" + id + " " + repository.getById(id);
    }
}
