package lt.karolis.demo.TaskManagementSystem.controller;

import lt.karolis.demo.TaskManagementSystem.persistance.SubTask;
import lt.karolis.demo.TaskManagementSystem.service.SubTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task/subtask")
public class SubTaskController {

    @Autowired
    SubTaskService service;

    @GetMapping
    public String getSubTask() {
        return "getSUB_TASK";
    }

    @GetMapping("/{id}")
    public SubTask getSubTaskById(@PathVariable Long id) {
        return service.getSubTaskById(id);
    }

    @GetMapping("/all")
    public List<SubTask> gettSubAllTasks() {
        return service.getAllTasks();
    }

    @PostMapping("/save")
    public SubTask createtSubTask(@RequestBody SubTask task) {
        System.out.println("asdasd");
        return service.createTask(task);
    }

//    @PutMapping(value = "/{id}")
//    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
//        return service.updateTask(id, task);
//    }

    @PutMapping()
    public SubTask updatetSubTask(@RequestParam Long id, @RequestBody SubTask task) {
        return service.updateTask(id, task);
    }

    @DeleteMapping()
    public String deletetSubTask(Long id) {
        service.deleteTask(id);
        return "delele succesful";
    }
}
