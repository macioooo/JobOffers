package org.joboffers.domain.loginandregistration;

class LoginAndRegistrationConfiguration {
    LoginAndRegistrationFacade createForTests(LoginAndRegistrationRepository repository) {
        LoginAndRegistrationValidator validator = new LoginAndRegistrationValidator(repository);
        return new LoginAndRegistrationFacade(repository, validator);
    }
}
