package lt.karolis.demo.TaskManagementSystem.controller;

import lt.karolis.demo.TaskManagementSystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping
    public String getTaskById(@RequestParam Long id) {
        return service.getTaskById(id);
    }
}
