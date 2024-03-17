package demo.boot.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.boot.application.TaskUseCase;
import demo.boot.domain.Task;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskUseCase taskUseCase;

    @Autowired
    public TaskController(TaskUseCase taskUseCase) {
        this.taskUseCase = taskUseCase;
    }

    @PostMapping
    public Task createTask(@RequestBody TaskRequest taskRequest) {
        return taskUseCase.createTask(taskRequest.getTitle());
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskUseCase.getTaskById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTaskById(@PathVariable Long id) {
        taskUseCase.deleteTaskById(id);
    }
}
