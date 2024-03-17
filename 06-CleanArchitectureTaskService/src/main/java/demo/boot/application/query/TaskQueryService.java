package demo.boot.application.query;

import java.util.List;

import org.springframework.stereotype.Service;

import demo.boot.domain.Task;
import demo.boot.infrastructure.TaskRepository;

@Service
public class TaskQueryService {
    private final TaskRepository taskRepository;

    public TaskQueryService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id);
    }
}