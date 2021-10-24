package lt.karolis.demo.TaskManagementSystem.controller;

import lt.karolis.demo.TaskManagementSystem.persistance.Task;
import lt.karolis.demo.TaskManagementSystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    TaskService service;

    @GetMapping
    public String getTask() {
        System.out.println("ASDasdasd");
        return "getTASK";
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return service.getTaskById(id);
    }

    @GetMapping("/all")
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }

    @PostMapping("/save")
    public Task createTask(@RequestBody Task task) {
        System.out.println("asdasd");
        return service.createTask(task);
    }

//    @PutMapping(value = "/{id}")
//    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
//        return service.updateTask(id, task);
//    }

    @PutMapping()
    public Task updateTask(@RequestParam Long id, @RequestBody Task task) {
        return service.updateTask(id, task);
    }

    @DeleteMapping()
    public String deleteTask(Long id) {
        service.deleteTask(id);
        return "delele succesful";
    }
}
