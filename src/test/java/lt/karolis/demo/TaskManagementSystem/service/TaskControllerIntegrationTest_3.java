package lt.karolis.demo.TaskManagementSystem.service;

import lt.karolis.demo.TaskManagementSystem.controller.TaskController;
import lt.karolis.demo.TaskManagementSystem.persistance.domain.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerIntegrationTest_3 {

    @Autowired
    TaskController taskController;

    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void createTaskTest() {
        Task task = new Task()
                .setTitle("Title Task")
                .setDescription("desription");
        String result = taskController.createTaskReturnString(task);
        Assert.assertEquals("success", result);
//        Assert.assertThat(result, is(equals("success")));
    }

    @Test
    public void createTaskMissingDescriptionTest() {
        Task task = new Task()
                .setTitle("Title Task")
                .setDescription(null);
        String result = taskController.createTaskReturnString(task);

        Assert.assertEquals("failure", result);
    }
}
