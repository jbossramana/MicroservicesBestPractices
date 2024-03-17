package demo.boot.application;

import demo.boot.domain.Task;

public class TaskService implements TaskUseCase {
    @Override
    public Task createTask(String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false); // Default value
        // Save task logic
        return task;
    }

    @Override
    public Task getTaskById(Long id) {
        // Retrieve task logic
        return null;
    }

    @Override
    public void deleteTaskById(Long id) {
        // Delete task logic
    }
}