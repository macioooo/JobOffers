package org.joboffers.domain.loginandregistration;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryLoginAndRegistrationRepositoryImpl implements LoginAndRegistrationRepository {
    private final Map<String, User> users = new ConcurrentHashMap<>();

    @Override
    public User save(User user) {

        User userWithId = User.builder().id("00" + (users.size() + 1))
                .login(user.login())
                .password(user.password())
                .build();
        users.put(user.login(), userWithId);
        return userWithId;
    }

    @Override
    public Optional<User> findByUsername(String login) {
        return Optional.ofNullable(users.get(login));
    }
}
