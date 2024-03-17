package demo.hexagon.application.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import demo.hexagon.application.port.DeleteUser;
import demo.hexagon.application.port.DisplayUser;
import demo.hexagon.application.port.ModifyUser;
import demo.hexagon.application.port.SubmitNewUser;
import demo.hexagon.application.port.commands.ModifyUserCommand;
import demo.hexagon.application.port.commands.SubmitNewUserCommand;
import demo.hexagon.domain.exception.UserNameAlreadyTaken;
import demo.hexagon.domain.exception.UserNotFound;
import demo.hexagon.domain.model.User;
import demo.hexagon.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
class UserService implements SubmitNewUser, DisplayUser, DeleteUser, ModifyUser {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User submit(final SubmitNewUserCommand submitNewUserCommand) {
        final String userName = submitNewUserCommand.getUserName();

        LOGGER.info("Submitting user with username: {}", userName);

        userRepository.findByUserName(userName)
                .ifPresent(user -> {
                    final String errorMessage = String.format("Username: %s is already taken!", userName);
                    LOGGER.error(errorMessage);
                    throw new UserNameAlreadyTaken(errorMessage);
                });

        final User user = new User.UserBuilder()
                .userName(userName)
                .firstName(submitNewUserCommand.getFirstName())
                .lastName(submitNewUserCommand.getLastName())
                .age(submitNewUserCommand.getAge())
                .build();

        final User submittedUser = userRepository.save(user);
        LOGGER.info("Submitting user with username: {} was successful!", userName);

        return submittedUser;
    }

    @Override
    public List<User> listAll() {
        return new ArrayList<>(userRepository.findAll());
    }

    @Override
    public User findByUserName(final String userName) {
        LOGGER.info("Finding user with username: {}", userName);
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> {
                    final String errorMessage = String.format("User was not found with the given username: %s", userName);
                    LOGGER.error(errorMessage);
                    throw new UserNotFound(errorMessage);
                });
    }

    @Override
    public void deleteByUserName(final String userName) {
        LOGGER.info("Finding user with username: {}", userName);
        userRepository.findByUserName(userName)
                .orElseThrow(() -> {
                    final String errorMessage = String.format("User was not found with the given username: %s", userName);
                    throw new UserNotFound(errorMessage);
                });

        userRepository.deleteByUserName(userName);
    }

    @Override
    public User modify(final String userName, final ModifyUserCommand modifyUserCommand) {
        LOGGER.info("Modifying user details of username: {}", userName);
        final User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> {
                    final String errorMessage = String.format("User was not found with the given username: %s", userName);
                    throw new UserNotFound(errorMessage);
                });

        if (modifyUserCommand.getFirstName() != null) {
            user.firstName(modifyUserCommand.getFirstName());
        }
        if (modifyUserCommand.getLastName() != null) {
            user.lastName(modifyUserCommand.getLastName());
        }
        if (modifyUserCommand.getAge() > 0) {
            user.age(modifyUserCommand.getAge());
        }
        final User modifiedUser = userRepository.save(user);
        LOGGER.info("Modifying user details of username: {} was successful!", userName);

        return modifiedUser;
    }
}
