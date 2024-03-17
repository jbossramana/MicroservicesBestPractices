package demo.hexagon.domain;

import org.junit.jupiter.api.Test;

import demo.hexagon.domain.model.User;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    void constructUser() {
        final User user = new User.UserBuilder()
                .userName("super")
                .firstName("super")
                .lastName("murali")
                .age(30)
                .build();

        assertThat(user.getUserName()).isEqualTo("super");
        assertThat(user.getFirstName()).isEqualTo("super");
        assertThat(user.getLastName()).isEqualTo("murali");
        assertThat(user.getAge()).isEqualTo(30);
    }
}
