package lt.karolis.demo.TaskManagementSystem.controller;

import io.swagger.models.Response;
import lt.karolis.demo.TaskManagementSystem.exception.TaskNotFoundException;
import lt.karolis.demo.TaskManagementSystem.persistance.domain.SubTask;
import lt.karolis.demo.TaskManagementSystem.persistance.domain.Task;
import lt.karolis.demo.TaskManagementSystem.service.SubTaskService;
import lt.karolis.demo.TaskManagementSystem.service.TaskService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("tasks/{taskId}/subtasks")
public class SubTaskController {

    //    @Autowired
    private final SubTaskService service;

    private final TaskService taskService;

    public SubTaskController(SubTaskService service, TaskService taskService) {
        this.service = service;
        this.taskService = taskService;
    }

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

    @PostMapping()
    public HttpEntity<SubTask> createSubTask(@RequestBody SubTask subTask, @PathVariable Long taskId) {
        System.out.println("createtSubTask");

        Task task =Optional.ofNullable(taskService.getTaskById(taskId))
                .orElseThrow(() -> new TaskNotFoundException("Not found:::: "+ taskId));

        subTask.setParentTask(task);
        service.createSubTask(subTask);
        return new ResponseEntity<SubTask>(subTask, HttpStatus.CREATED);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
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
