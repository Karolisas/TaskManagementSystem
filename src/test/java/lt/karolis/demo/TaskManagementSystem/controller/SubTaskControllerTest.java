package lt.karolis.demo.TaskManagementSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lt.karolis.demo.TaskManagementSystem.exception.TaskNotFoundException;
import lt.karolis.demo.TaskManagementSystem.persistance.Priority;
import lt.karolis.demo.TaskManagementSystem.persistance.domain.SubTask;
import lt.karolis.demo.TaskManagementSystem.persistance.domain.Task;
import lt.karolis.demo.TaskManagementSystem.persistance.repository.TaskRepository;
import lt.karolis.demo.TaskManagementSystem.service.SubTaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest
public class SubTaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SubTaskService subTaskService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createSubTask() throws Exception {
        SubTask subTask = new SubTask()
                .setDescription("BBBBBC")
                .setLevelPriority(Priority.DONE);


        mockMvc.perform(MockMvcRequestBuilders.post("/tasks/{taskId}/subtasks", 186l)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(subTask)
//                        .content(new ObjectMapper().writeValueAsString(subTask)
                        ))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}