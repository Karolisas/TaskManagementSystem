package lt.karolis.demo.TaskManagementSystem.persistance;

import junit.textui.TestRunner;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PriorityConverterTest {

    private PriorityConverter converter;

//    @BeforeAll
//    public void setUp() {
//        converter = new PriorityConverter();
//    }

    @Test
    public void convertToEntityAttribute_passNull() {
        converter = new PriorityConverter();

        assertNull(converter.convertToEntityAttribute(null));
    }

    @Test
    public void convertToEntityAttribute_passPorperValue() {
        converter = new PriorityConverter();

        assertEquals(Priority.TO_DO, converter.convertToEntityAttribute(10));
    }

}