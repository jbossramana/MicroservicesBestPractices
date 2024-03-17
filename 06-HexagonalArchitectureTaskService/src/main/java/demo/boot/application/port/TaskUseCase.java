package demo.boot.application.port;

import demo.boot.domain.Task;

public interface TaskUseCase {
    Task createTask(String title);
    Task getTaskById(Long id);
    void deleteTaskById(Long id);
}