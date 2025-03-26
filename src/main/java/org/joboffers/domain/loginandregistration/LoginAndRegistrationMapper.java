package org.joboffers.domain.loginandregistration;

import org.joboffers.domain.loginandregistration.dto.RegistrationResultDto;

import static org.joboffers.domain.loginandregistration.LoginAndRegistrationMessages.ACCOUNT_SUCCESSFULLY_CREATED;

class LoginAndRegistrationMapper {

    static RegistrationResultDto mapFromUserToRegistrationResultDto(User user) {
        return RegistrationResultDto.builder()
                .message(ACCOUNT_SUCCESSFULLY_CREATED.message)
                .login(user.login())
                .id(user.id())
                .build();
    }
}
