package lt.karolis.demo.TaskManagementSystem.persistance;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TaskRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TaskRepository taskRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test @Order(1)
    public void agetTaskById() {
        Task task = new Task();
//        task.setId(34L); // if Id is set -> org.hibernate.PersistentObjectException: detached entity passed to persist:
        task.setTitle("Test");

//        taskRepository.save(task);
        testEntityManager.persist(task);
        Task retrievedTask = taskRepository.getByTitle("Test");
        Assert.assertEquals(task, retrievedTask);
    }

    @Test @Order(20) @AfterAll
    public void findAll() {
        Task task = new Task();
        task.setTitle("TestAll");
        testEntityManager.persist(task);
        testEntityManager.persist(task);
        List<Task> retrievedTasks = taskRepository.findAll();
        Assert.assertEquals(4,retrievedTasks.size());
//        Assert.assertEquals(retrievedTasks, Stream.of(task, task).collect(Collectors.toList()));


    }
}