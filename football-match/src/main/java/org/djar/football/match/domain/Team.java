package org.djar.football.match.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class Team {

    private final String clubId;

}
