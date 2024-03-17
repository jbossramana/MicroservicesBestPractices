package demo.hexagon.adapter.persistence.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import demo.hexagon.adapter.persistence.jpa.mapper.UserModelToDomainMapper;
import demo.hexagon.adapter.persistence.jpa.model.UserModel;
import demo.hexagon.adapter.persistence.jpa.repository.UserJpaRepository;
import demo.hexagon.domain.model.User;
import demo.hexagon.domain.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
class UserJpaAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final Function<UserModel, User> TO_DOMAIN = new UserModelToDomainMapper();

    @Autowired
    public UserJpaAdapter(final UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User save(final User user) {
        final UserModel userModel = userJpaRepository.save(UserModel.of(user));
        return TO_DOMAIN.apply(userModel);
    }

    @Override
    public Optional<User> findByUserName(final String userName) {
        return userJpaRepository.findByUserName(userName)
                .map(TO_DOMAIN);
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll()
                .stream()
                .map(TO_DOMAIN)
                .toList();
    }

    @Override
    public void deleteByUserName(final String userName) {
        userJpaRepository.deleteByUserName(userName);
    }

}
