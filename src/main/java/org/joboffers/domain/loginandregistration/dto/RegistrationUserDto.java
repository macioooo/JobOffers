package org.joboffers.domain.loginandregistration.dto;

import lombok.Builder;

@Builder
public record RegistrationUserDto(String login, String password) {
}
