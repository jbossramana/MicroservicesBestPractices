package demo.hexagon.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import demo.hexagon.application.port.commands.SubmitNewUserCommand;
import demo.hexagon.application.service.UserService;
import demo.hexagon.domain.exception.UserNameAlreadyTaken;
import demo.hexagon.domain.model.User;
import demo.hexagon.domain.repository.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubmitNewUserTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void before() {
        reset(userRepository);
    }

    @Test
    void testSubmitNewUser() {
        final String userName = "super";
        final String firstName = "super";
        final String lastName = "murali";
        final int age = 45;

        final User user = new User.UserBuilder()
                .userName(userName)
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .build();

        when(userRepository.findByUserName(userName)).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);

        final User submittedUser = userService.submit(
                new SubmitNewUserCommand.SubmitNewUserCommandBuilder()
                        .userName(userName)
                        .firstName(firstName)
                        .lastName(lastName)
                        .age(age)
                        .build());

        assertThat(submittedUser.getUserName()).isEqualTo(userName);
        assertThat(submittedUser.getFirstName()).isEqualTo(firstName);
        assertThat(submittedUser.getLastName()).isEqualTo(lastName);
        assertThat(submittedUser.getAge()).isEqualTo(age);

        verify(userRepository, timeout(1)).findByUserName(anyString());
        verify(userRepository, timeout(1)).save(any());
    }

    @Test
    void testSubmitNewUserThrowsUserNameAlreadyTakenException() {
        final String userName = "super";
        final String firstName = "super";
        final String lastName = "murali";
        final int age = 45;

        final User user = new User.UserBuilder()
                .userName(userName)
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .build();

        when(userRepository.findByUserName(userName))
                .thenReturn(Optional.empty())
                .thenReturn(Optional.of(user));

        when(userRepository.save(user)).thenReturn(user);

        final SubmitNewUserCommand submitCommand = new SubmitNewUserCommand.SubmitNewUserCommandBuilder()
                .userName(userName)
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .build();

        final User submittedUser = userService.submit(submitCommand);

        assertThat(submittedUser.getUserName()).isEqualTo(userName);
        assertThat(submittedUser.getFirstName()).isEqualTo(firstName);
        assertThat(submittedUser.getLastName()).isEqualTo(lastName);
        assertThat(submittedUser.getAge()).isEqualTo(age);

        final SubmitNewUserCommand submitCommandAgain = new SubmitNewUserCommand.SubmitNewUserCommandBuilder()
                .userName("super")
                .firstName("super")
                .lastName("murali")
                .build();

        assertThatThrownBy(() -> userService.submit(submitCommandAgain))
                .isInstanceOf(UserNameAlreadyTaken.class)
                .hasMessage(String.format("Username: %s is already taken!", userName));


        verify(userRepository, times(2)).findByUserName(anyString());
        verify(userRepository, times(1)).save(any());

    }

}
