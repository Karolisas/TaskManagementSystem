package lt.karolis.demo.TaskManagementSystem.service;

import lt.karolis.demo.TaskManagementSystem.controller.TaskController;
import lt.karolis.demo.TaskManagementSystem.persistance.Priority;
import lt.karolis.demo.TaskManagementSystem.persistance.SubTask;
import lt.karolis.demo.TaskManagementSystem.persistance.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {

    @Autowired
    TaskController taskController;

    @Autowired
    TaskService taskService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void areSubtasksNotDoneTest() {
        Task task = new Task();
        task.setId(100L).setTitle("Test").setDescription("description").setLevelPriority(Priority.TO_DO)
                .setSubTasks(Arrays.asList(
                        new SubTask().setId(555L).setLevelPriority(Priority.DONE),
                        new SubTask().setId(556L).setLevelPriority(Priority.TO_DO)));

        Assert.assertEquals(true, taskService.areSubtasksNotDone(task));

        task.setSubTasks(Arrays.asList(
                new SubTask().setId(555L).setLevelPriority(Priority.TO_DO),
                new SubTask().setId(556L).setLevelPriority(Priority.IN_PROGRESS)));

        Assert.assertEquals(true, taskService.areSubtasksNotDone(task));

        task.setSubTasks(Arrays.asList(
                new SubTask().setId(555L).setLevelPriority(Priority.DONE),
                new SubTask().setId(556L).setLevelPriority(Priority.DONE)));

        Assert.assertEquals(false, taskService.areSubtasksNotDone(task));

    }
}