package lt.karolis.demo.TaskManagementSystem;

import lt.karolis.demo.TaskManagementSystem.controller.TaskController;
import lt.karolis.demo.TaskManagementSystem.persistance.domain.Task;
import lt.karolis.demo.TaskManagementSystem.persistance.repository.TaskRepository;
import lt.karolis.demo.TaskManagementSystem.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class TaskManagementSystemApplicationTests {

    @Autowired
    TaskService service;

    @Autowired
    TaskRepository repository;

    @Test
    void contextLoads() {
//        repository.findById(101l)
//        TaskController taskController = new TaskControllerTest(service,repository);
        TaskController taskController = new TaskController();
        taskController.createTask(new Task().setId(666L).setDescription("asdfadf"));
//        Assert.assertEquals("asdfadf", taskController.getTaskById(666l).getDescription());
    }


}
