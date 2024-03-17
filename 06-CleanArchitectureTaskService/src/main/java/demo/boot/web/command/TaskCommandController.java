package demo.boot.web.command;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.boot.application.command.TaskCommandService;
import demo.boot.domain.Task;

@RestController
@RequestMapping("/commands/tasks")
public class TaskCommandController {
    private final TaskCommandService taskCommandService;

    public TaskCommandController(TaskCommandService taskCommandService) {
        this.taskCommandService = taskCommandService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskRequest taskRequest) {
        Task task = taskCommandService.createTask(taskRequest.getTitle());
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
        taskCommandService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}