package demo.boot.domain;

public class Task {
    private Long id;
    private String title;
    private boolean completed;

    // Default constructor for Jackson deserialization
    public Task() {
    }

    public Task(String title) {
        this.title = title;
        this.completed = false; // Default value
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}