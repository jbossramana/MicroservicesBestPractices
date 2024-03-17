package demo.hexagon.domain.model;

import java.util.Objects;

public class User {
    private Integer id;
    private String userName;
    private String firstName;
    private String lastName;
    private int age;

    private User(final UserBuilder userBuilder) {
        this.id = userBuilder.id;
        this.userName = userBuilder.userName;
        this.firstName = userBuilder.firstName;
        this.lastName = userBuilder.lastName;
        this.age = userBuilder.age;
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public User userName(final String userName) {
        this.userName = userName;
        return this;
    }

    public User firstName(final String firstName) {
        this.firstName = firstName;
        return this;
    }

    public User lastName(final String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User age(final int age) {
        this.age = age;
        return this;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(id, user.id) && Objects.equals(userName, user.userName) &&
                Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, firstName, lastName, age);
    }

    public static class UserBuilder {
        private Integer id;
        private String userName;
        private String firstName;
        private String lastName;
        private int age;

        public User build() {
            return new User(this);
        }

        public UserBuilder id(final Integer id) {
            this.id = id;
            return this;
        }

        public UserBuilder userName(final String userName) {
            this.userName = userName;
            return this;
        }

        public UserBuilder firstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder age(final int age) {
            this.age = age;
            return this;
        }
    }
}
