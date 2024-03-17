package demo.boot.web.command;


public class TaskRequest {
    private String title;

    // Default constructor for Jackson deserialization
    public TaskRequest() {
    }

    public TaskRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}