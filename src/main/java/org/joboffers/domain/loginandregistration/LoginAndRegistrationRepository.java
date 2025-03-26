package org.joboffers.domain.loginandregistration;

import java.util.Optional;

interface LoginAndRegistrationRepository {
    User save(User user);
    Optional<User> findByUsername(String login);

}
