package azure.vuksa.apimanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class TodosController {

    public TodosController() {
        todos = new HashMap<>();
        final Todo todo = new Todo(UUID.randomUUID().toString(), "Learn Azure");
        todos.put(todo.getId(), todo);
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable("id") String id) {
        final Todo todo = todos.get(id);
        return todo == null ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(todo);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getAll() {
        return ResponseEntity.ok(new ArrayList<>(todos.values()));
    }

    @PostMapping("/todo")
    public ResponseEntity<Todo> createTodo(@RequestBody CreateTodoDto createTodoDto) {
        final Todo todo = new Todo(UUID.randomUUID().toString(), createTodoDto.getText());
        todos.put(todo.getId(), todo);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<Todo> createTodo(@PathVariable("id") String id) {
        final Todo todo = todos.remove(id);
        return todo == null ? ResponseEntity.notFound().build() : ResponseEntity.ok().build();
    }

    private final Map<String, Todo> todos;


}
