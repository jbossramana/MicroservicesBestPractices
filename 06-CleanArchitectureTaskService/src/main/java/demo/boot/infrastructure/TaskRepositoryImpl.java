package demo.boot.infrastructure;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import demo.boot.domain.Task;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
    private final List<Task> tasks = new ArrayList<>();
    private Long idCounter = 1L;

    @Override
    public Task save(Task task) {
        task.setId(idCounter++);
        tasks.add(task);
        return task;
    }

    @Override
    public Task findById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Task> findAll() {
        return tasks;
    }

    @Override
    public void deleteById(Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }
}