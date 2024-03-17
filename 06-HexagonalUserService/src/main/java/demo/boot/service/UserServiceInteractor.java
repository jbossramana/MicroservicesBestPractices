package demo.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.boot.model.User;
import demo.boot.repository.UserRepositoryPort;

@Service
public class UserServiceInteractor  {

    @Autowired
    private UserRepositoryPort userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

 
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }


    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

  
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}