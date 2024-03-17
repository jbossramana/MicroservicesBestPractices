package demo.boot.model;

// Value Object - Email:

public class Email {
    private final String value;

    public Email(String value) {
        // Add validation for email format if necessary
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // Add methods for email validation, comparison, etc. if necessary
}