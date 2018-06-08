package pl.agh.bit.javaoop.springtodolist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import pl.agh.bit.javaoop.springtodolist.model.Todo;
import pl.agh.bit.javaoop.springtodolist.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class TodoControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private TodoRepository todoRepository;

    private MockMvc mockMvc;
    private List<Todo> todoList = new ArrayList<>();

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.todoRepository.deleteAllInBatch();

        todoList.add(todoRepository.save(new Todo("Ubrac sie", "12:30")));
        todoList.add(todoRepository.save(new Todo("Zjesc cos", "16:30")));
    }

    @Test
    public void shouldGetRightTodo() throws Exception {
        //given
        Todo testedTodo = todoList.get(0);
        //when-then
        mockMvc.perform(get("/todos/" + testedTodo.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", is(testedTodo.getText())))
                .andExpect(jsonPath("$.hour", is(testedTodo.getHour())));
    }
}
