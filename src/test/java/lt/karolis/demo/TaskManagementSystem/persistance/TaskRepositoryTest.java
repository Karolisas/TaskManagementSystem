package lt.karolis.demo.TaskManagementSystem.persistance;

import lt.karolis.demo.TaskManagementSystem.service.TaskService;
import org.junit.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2) // dont work
public class TaskRepositoryTest {


    @Autowired
    private TestEntityManager testEntityManager; // directly saves to DB

    @Autowired
    private TaskRepository taskRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test (expected = ConstraintViolationException.class)
    @Order(1)
    public void createTaskConstrainsViolation() {
        Task task = new Task();
//        task.setId(34L); // if Id is set -> org.hibernate.PersistentObjectException: detached entity passed to persist:
        task.setTitle("Test"); // min title 5

        testEntityManager.persist(task);
//        Task retrievedTask = taskRepository.getByTitle("Test");
        Assert.assertEquals(1, taskRepository.findAll());
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    @Order(2)
    @Ignore // how to get exception message
    public void bgetTaskById() {
//        exceptionRule.expect(NumberFormatException.class);
//        exceptionRule.expectMessage("For input string");
//        Integer.parseInt("1a");

        Task task = new Task();
        task.setTitle("Test");

//        taskRepository.save(task);
        exceptionRule.expect(ConstraintViolationException.class);
        exceptionRule.expectMessage("Name failed");
        testEntityManager.persist(task);
    }

    @Test
    @Order(20)
    @AfterAll
    public void findAll() {
        Task task = new Task();
        task.setTitle("TestAll");
        testEntityManager.persist(task);
        testEntityManager.persist(task);
        List<Task> retrievedTasks = taskRepository.findAll();
//        List<Task> retrievedTasks = testEntityManager.(Task.class).listfindAll();
        Assert.assertTrue(0 < retrievedTasks.size());
//        Assert.assertEquals(retrievedTasks, Stream.of(task, task).collect(Collectors.toList()));


    }
}