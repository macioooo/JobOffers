package org.joboffers.domain.loginandregistration;

import java.util.Optional;

class LoginAndRegistrationValidator {
    LoginAndRegistrationRepository repository;

    LoginAndRegistrationValidator(LoginAndRegistrationRepository repository) {
        this.repository = repository;
    }


    boolean userAlreadyExists(String userName) {
        Optional<User> checkUser = repository.findByUsername(userName);
        return !checkUser.isEmpty();
    }
}
