package demo.hexagon.adapter.persistence.jpa.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import demo.hexagon.domain.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "USER")
@Entity
@DynamicInsert
@DynamicUpdate
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @NotNull
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "AGE", nullable = false)
    private int age;

    public UserModel() {
    }

    private UserModel(final UserModel.UserModelBuilder userModelBuilder) {
        this.id = userModelBuilder.id;
        this.userName = userModelBuilder.userName;
        this.firstName = userModelBuilder.firstName;
        this.lastName = userModelBuilder.lastName;
        this.age = userModelBuilder.age;
    }

    public static UserModel of(final User user) {
        final UserModelBuilder userModelBuilder = new UserModelBuilder();
        if (user.getId() != null) {
            userModelBuilder.id(user.getId().longValue());
        }
        return userModelBuilder
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .userName(user.getUserName())
                .build();
    }

    public Long getId() {
        return id;
    }

    public UserModel id(final Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserModel firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserModel lastName(final String lastName) {
        this.lastName = lastName;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserModel age(int age) {
        this.age = age;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserModel userName(final String userName) {
        this.userName = userName;
        return this;
    }

    public static class UserModelBuilder {
        private Long id;
        private String userName;
        private String firstName;
        private String lastName;
        private int age;

        public UserModel build() {
            return new UserModel(this);
        }

        public UserModel.UserModelBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public UserModel.UserModelBuilder userName(final String userName) {
            this.userName = userName;
            return this;
        }

        public UserModel.UserModelBuilder firstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserModel.UserModelBuilder lastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserModel.UserModelBuilder age(final int age) {
            this.age = age;
            return this;
        }
    }
}
