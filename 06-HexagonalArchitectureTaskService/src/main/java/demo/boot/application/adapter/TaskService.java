package demo.boot.application.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.boot.application.port.TaskRepository;
import demo.boot.application.port.TaskUseCase;
import demo.boot.domain.Task;

@Service
public class TaskService implements TaskUseCase {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(String title) {
        Task task = new Task();
        task.setTitle(title);
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }
}