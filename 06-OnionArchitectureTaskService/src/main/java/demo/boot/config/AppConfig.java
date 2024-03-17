package demo.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import demo.boot.application.TaskService;
import demo.boot.application.TaskUseCase;
import demo.boot.infrastructure.TaskRepository;
import demo.boot.infrastructure.TaskRepositoryImpl;

@Configuration
public class AppConfig {
    @Bean
    public TaskUseCase taskUseCase(TaskRepository taskRepository) {
        return new TaskService();
    }

    @Bean
    public TaskRepository taskRepository() {
        return new TaskRepositoryImpl();
    }
}