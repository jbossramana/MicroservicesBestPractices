package demo.hexagon.adapter.persistence.inmemory;

import org.springframework.stereotype.Repository;

import demo.hexagon.domain.model.User;
import demo.hexagon.domain.repository.UserRepository;

import java.util.*;

@Repository
class UserInMemoryDatabaseAdapter implements UserRepository {

    private static final Map<String, User> IN_MEMORY_MAP = new HashMap<>();

    @Override
    public User save(final User user) {
        IN_MEMORY_MAP.put(user.getUserName(), user);
        return user;
    }

    @Override
    public Optional<User> findByUserName(final String userName) {
        final User user = IN_MEMORY_MAP.get(userName);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(IN_MEMORY_MAP.values());
    }

    @Override
    public void deleteByUserName(final String userName) {
        final User user = IN_MEMORY_MAP.get(userName);
        if (user != null) {
            IN_MEMORY_MAP.remove(userName);
        }
    }
}
