package lt.karolis.demo.TaskManagementSystem.service;

import lt.karolis.demo.TaskManagementSystem.persistance.Task;
import lt.karolis.demo.TaskManagementSystem.persistance.TaskRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TaskServiceMockitoTest_2 {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void injectingMockstest() {
        Assert.assertNotNull(taskService.repository);
    }

    @Test
    public void createTaskTestWithDescription() {
        Task mockTask = new Task().setTitle("Title Task").setDescription("desription");
        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(mockTask);

        Assert.assertEquals(mockTask, taskService.createTask(new Task().setDescription("asda")));
    }

    @Test
    public void createFailTaskTestWithoutDescription() {
        Assert.assertEquals(null, taskService.createTask(new Task().setDescription(null)));
    }
}
