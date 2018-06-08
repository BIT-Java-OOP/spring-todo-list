package pl.agh.bit.javaoop.springtodolist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.agh.bit.javaoop.springtodolist.repository.TodoRepository;
import pl.agh.bit.javaoop.springtodolist.model.Todo;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public ResponseEntity readTodos(@RequestParam(value = "text", required = false) String text) {
        if(text != null) {
            return ResponseEntity.ok(todoRepository.findByMessage(text));
        }
        return ResponseEntity.ok(todoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity readTodo(@PathVariable long id) {
        return ResponseEntity.ok(todoRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity addTodo(@RequestBody Todo todoItem) {
        Todo todo = todoRepository.save(todoItem);
        return ResponseEntity.ok(todo.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity replaceTodo(@PathVariable long id, @RequestBody Todo todoItem) {
        Todo todo = todoRepository.getOne(id);
        todo.setText(todoItem.getText());
        todo.setHour(todoItem.getHour());
        todoRepository.save(todo);
        return ResponseEntity.ok(todo.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodo(@PathVariable long id) {
        todoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
