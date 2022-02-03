package lt.karolis.demo.TaskManagementSystem.controller;

import lt.karolis.demo.TaskManagementSystem.exception.TaskNotFoundException;
import lt.karolis.demo.TaskManagementSystem.persistance.Task;
import lt.karolis.demo.TaskManagementSystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    TaskService service;

    @Autowired
    MessageSource messageSource;

    @GetMapping
    public String getTask() {
        System.out.println("ASDasdasd");
        return "getTASK";
    }

    @GetMapping(value = "/internationalized", produces = MediaType.TEXT_PLAIN_VALUE)
        public String getTaskInternational(@RequestHeader(name = "Accept-Language", required = true) Locale locale) {
        return messageSource.getMessage("good.morning.message", null, locale);
    }
    @GetMapping("/internationalized2")
    public String getTaskInternational2(){
//            @RequestHeader(name = "Accept-Language", required = false) Locale locale)

        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }

    @GetMapping("/{id}")
    public EntityModel<Task> getTaskById(@PathVariable Long id) {
        Task task = service.getTaskById(id);
        if (task == null)
            throw new TaskNotFoundException("No task Found with id: " + id);

        EntityModel<Task> taskEntityModel = EntityModel.of(task);
        Link linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllTasks()).withRel("all-users");
        taskEntityModel.add(linkTo);

        return taskEntityModel;
    }

    @GetMapping("/all")
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }

    @PostMapping("/save")
    public HttpEntity<Task> createTask(@RequestBody @Valid Task task) {
        System.out.println("Create Task");
        Task savedTask = service.createTask(task);

        if (savedTask != null) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedTask.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
        return ResponseEntity.badRequest().build();
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
