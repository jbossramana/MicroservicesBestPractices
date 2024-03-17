package demo.boot.infrastructure;

import demo.boot.domain.Task;

public  class TaskRepositoryImpl implements TaskRepository {

    public Task save(Task task) {
        // Save task to database logic
        return task;
    }

  
    public Task findById(Long id) {
        // Find task by ID in database logic
        return new Task();
    }

 
    public void deleteById(Long id) {
        // Delete task by ID from database logic
    }
}