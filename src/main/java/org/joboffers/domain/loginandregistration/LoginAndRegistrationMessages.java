package org.joboffers.domain.loginandregistration;

enum LoginAndRegistrationMessages {
    EMAIL_ALREADY_TAKEN("Sorry, this email is already taken. Please try with another one"),
    USERNAME_ALREADY_TAKEN("Sorry, this username is already taken. Please try with another one"),
    WRONG_LOGIN_OR_PASSWORD("Wrong login or password, please try again"),
    ACCOUNT_SUCCESSFULLY_CREATED("Account was created successfully!"),
    USER_NOT_FOUND("User not found");
    ;
    final String message;

    LoginAndRegistrationMessages(String message) {
        this.message = message;
    }
}
