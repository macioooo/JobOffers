package org.joboffers.domain.loginandregistration;

import lombok.AllArgsConstructor;
import org.joboffers.domain.loginandregistration.dto.RegistrationResultDto;
import org.joboffers.domain.loginandregistration.dto.RegistrationUserDto;
import org.joboffers.domain.loginandregistration.dto.UserDto;

import static org.joboffers.domain.loginandregistration.LoginAndRegistrationMapper.mapFromUserToRegistrationResultDto;
import static org.joboffers.domain.loginandregistration.LoginAndRegistrationMessages.USERNAME_ALREADY_TAKEN;

@AllArgsConstructor
public class LoginAndRegistrationFacade {
    private final LoginAndRegistrationRepository repository;
    private final LoginAndRegistrationValidator validator;


    public UserDto findByUserName(String login) {
        return repository.findByUsername(login).map(user -> new UserDto(user.id(), user.login(), user.password()))
                .orElseThrow(() -> new UsernameNotFoundException("USER_NOT_FOUND"));
    }

    public RegistrationResultDto register(RegistrationUserDto registrationUserDto) {
        if (validator.userAlreadyExists(registrationUserDto.login())) {
            return RegistrationResultDto.builder()
                    .message(USERNAME_ALREADY_TAKEN.message)
                    .build();
        }
        final User user = User.builder()
                .login(registrationUserDto.login())
                .password(registrationUserDto.password())
                .build();
        User savedUser = repository.save(user);
        return mapFromUserToRegistrationResultDto(savedUser);
    }
}
