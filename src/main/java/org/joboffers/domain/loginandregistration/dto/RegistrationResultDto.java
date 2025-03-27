package org.joboffers.domain.loginandregistration.dto;

import lombok.Builder;

@Builder
public record RegistrationResultDto(String id, String login, String message) {
}
