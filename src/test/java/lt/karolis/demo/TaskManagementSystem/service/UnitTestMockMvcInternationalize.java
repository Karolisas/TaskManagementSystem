package lt.karolis.demo.TaskManagementSystem.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;
import java.util.Locale;

@RunWith(SpringRunner.class)
@WebMvcTest()
public class UnitTestMockMvcInternationalize {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MessageSource messageSource;

//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    public void getFrench() throws Exception {
//        when(taskService.getAllTasks()).thenReturn(List.of(new Task(), new Task()));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/task/internationalized")
                .header("Accept-Language", Locale.FRENCH);
        ResultActions resultActions = mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    public void getFrenchContext() throws Exception {
//        when(taskService.getAllTasks()).thenReturn(List.of(new Task(), new Task()));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/task/internationalized2");
        ResultActions resultActions = mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    public void localeTest() {
        Assert.assertEquals("BonJour", messageSource.getMessage("good.morning.message", null, Locale.FRENCH));
        Assert.assertEquals("Good Morning", messageSource.getMessage("good.morning.message", null, Locale.ENGLISH));

    }

}
