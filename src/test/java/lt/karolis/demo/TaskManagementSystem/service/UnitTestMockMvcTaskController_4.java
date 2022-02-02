package lt.karolis.demo.TaskManagementSystem.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.karolis.demo.TaskManagementSystem.controller.TaskController;
import lt.karolis.demo.TaskManagementSystem.persistance.SubTaskRepository;
import lt.karolis.demo.TaskManagementSystem.persistance.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

import java.nio.charset.Charset;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
//@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
@WebMvcTest()
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestConfig.class)
//@WebAppConfiguration
//@ContextConfiguration(classes=OrderServiceConfig.class, loader=AnnotationConfigContextLoader.class)

public class UnitTestMockMvcTaskController_4 {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @Mock
    SubTaskRepository subTaskRepository;
    @Mock
    SubTaskService service;

    @MockBean
    TaskService taskService;

    @InjectMocks
    TaskController taskController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getMappingTest() throws Exception {
        when(taskService.getAllTasks()).thenReturn(List.of(new Task(), new Task()));

        mockMvc.perform(MockMvcRequestBuilders.get("/task/all"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAddTaskHappyPath() throws Exception {
        Task task = new Task();
        task.setDescription("aaaa");

        when(taskService.createTask(task)).thenReturn(task);

        mockMvc
                .perform(MockMvcRequestBuilders.post("/task/save", task)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(task)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();


//        Assert.assertEquals("failure", taskController.createTask2(task));

    }

    @Test
    public void testAddTaskUnHappyPath() throws Exception {
        Task task = new Task();

        when(taskService.createTask(ArgumentMatchers.any(Task.class))).thenReturn(null);

        mockMvc
                .perform(MockMvcRequestBuilders.post("/task/save", task)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(task)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();


//        Assert.assertEquals("failure", taskController.createTask2(task));

    }

    @Test
    public void getTask_throwsException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/task/get/500")
                        .contentType(APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }
}
