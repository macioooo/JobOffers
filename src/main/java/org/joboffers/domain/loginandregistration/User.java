package org.joboffers.domain.loginandregistration;

import lombok.Builder;

@Builder
public record User(String id, String login, String password) {
}
