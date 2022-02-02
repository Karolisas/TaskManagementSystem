package lt.karolis.demo.TaskManagementSystem.controller;

import lt.karolis.demo.TaskManagementSystem.exception.TaskNotFoundException;
import lt.karolis.demo.TaskManagementSystem.persistance.Task;
import lt.karolis.demo.TaskManagementSystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
        Task task = service.getTaskById(id);
        if (task == null)
            throw new TaskNotFoundException("No task Found with id: " + id);
        return task;
    }

    @GetMapping("/all")
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }

    @PostMapping("/save")
    public HttpEntity<Task> createTask(@RequestBody @Valid Task task) {
        System.out.println("Create Task");
        Task savedTask = service.createTask(task);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTask.getId()).toUri();

        ResponseEntity<Task> responseEntity = ResponseEntity.created(location).build();
        return responseEntity;
    }

    @PostMapping("/savee")
    public String createTaskReturnString(@RequestBody Task task) {
        Task newTask = service.createTask(task);
        if (newTask != null)
            return "success";
        return "failure";
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

    @PutMapping(value = "/closeTask")
    public Task closeTask(@RequestParam Long id) {
        return service.closeTask(id);
    }

}
