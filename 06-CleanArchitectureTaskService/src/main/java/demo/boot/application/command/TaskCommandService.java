package demo.boot.application.command;

import org.springframework.stereotype.Service;

import demo.boot.domain.Task;
import demo.boot.infrastructure.TaskRepository;

@Service
public class TaskCommandService {
    private final TaskRepository taskRepository;

    public TaskCommandService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(String title) {
        Task task = new Task();
        task.setTitle(title);
        return taskRepository.save(task);
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }
}