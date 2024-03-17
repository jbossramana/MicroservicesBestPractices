package demo.boot.infrastructure;

import java.util.List;

import demo.boot.domain.Task;

public interface TaskRepository {
    Task save(Task task);
    Task findById(Long id);
    List<Task> findAll();
    void deleteById(Long id);
}