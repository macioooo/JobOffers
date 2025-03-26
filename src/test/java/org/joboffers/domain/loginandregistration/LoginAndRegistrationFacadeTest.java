package org.joboffers.domain.loginandregistration;

import org.joboffers.domain.loginandregistration.dto.RegistrationResultDto;
import org.joboffers.domain.loginandregistration.dto.RegistrationUserDto;
import org.joboffers.domain.loginandregistration.dto.UserDto;
import org.junit.jupiter.api.Test;

import static org.joboffers.domain.loginandregistration.LoginAndRegistrationMessages.ACCOUNT_SUCCESSFULLY_CREATED;
import static org.joboffers.domain.loginandregistration.LoginAndRegistrationMessages.USERNAME_ALREADY_TAKEN;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LoginAndRegistrationFacadeTest {
    @Test
    void should_save_user_and_return_success_result() {
        //given
        LoginAndRegistrationRepository repository = new InMemoryLoginAndRegistrationRepositoryImpl();
        LoginAndRegistrationFacade facade = new LoginAndRegistrationConfiguration().createForTests(repository);
        RegistrationResultDto expectedResult = RegistrationResultDto.builder()
                .id("001")
                .login("Test")
                .message(ACCOUNT_SUCCESSFULLY_CREATED.message)
                .build();
        //when
        RegistrationResultDto resultDto = facade.register(RegistrationUserDto.builder()
                .login("Test")
                .password("Test")
                .build());
        //then
        assertEquals(expectedResult, resultDto);


    }

    @Test
    void should_return_user_already_exists_result() {
        //given
        LoginAndRegistrationRepository repository = new InMemoryLoginAndRegistrationRepositoryImpl();
        LoginAndRegistrationFacade facade = new LoginAndRegistrationConfiguration().createForTests(repository);
        //when
        RegistrationResultDto resultDto = facade.register(RegistrationUserDto.builder()
                .login("Test")
                .password("Test")
                .build());
        RegistrationResultDto resultDtoTwo = facade.register(RegistrationUserDto.builder()
                .login("Test")
                .password("Test")
                .build());
        //then
        assertEquals(RegistrationResultDto.builder().message(USERNAME_ALREADY_TAKEN.message).build(), resultDtoTwo);
    }

    @Test
    void should_find_user_and_return_dto() {
        //given
        LoginAndRegistrationRepository repository = new InMemoryLoginAndRegistrationRepositoryImpl();
        LoginAndRegistrationFacade facade = new LoginAndRegistrationConfiguration().createForTests(repository);
        RegistrationResultDto resultDto = facade.register(RegistrationUserDto.builder()
                .login("Test")
                .password("Test")
                .build());
        UserDto expectedUserDto = UserDto.builder()
                .id("001")
                .login("Test")
                .password("Test")
                .build();
        //when
        UserDto userDto = facade.findByUserName("Test");
        //then
        assertEquals(expectedUserDto, userDto);
    }

    @Test
    void should_throw_username_not_found_exception() {
        //given
        LoginAndRegistrationRepository repository = new InMemoryLoginAndRegistrationRepositoryImpl();
        LoginAndRegistrationFacade facade = new LoginAndRegistrationConfiguration().createForTests(repository);
        //when//then
        assertThrows(UsernameNotFoundException.class, () -> facade.findByUserName("Test"), "USER_NOT_FOUND");

    }
}
