package demo.boot.application.port;

import demo.boot.domain.Task;

public interface TaskRepository {
    Task save(Task task);
    Task findById(Long id);
    void deleteById(Long id);
}
