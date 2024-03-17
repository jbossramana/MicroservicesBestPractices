package demo.boot.infrastructure;

import demo.boot.domain.Task;

public interface TaskRepository {

    public Task save(Task task);
       
    public Task findById(Long id);
 
    public void deleteById(Long id) ;
    
}
