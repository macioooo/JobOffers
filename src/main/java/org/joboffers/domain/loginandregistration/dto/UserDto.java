package org.joboffers.domain.loginandregistration.dto;

import lombok.Builder;

@Builder
public record UserDto(String id, String login, String password) {
}
