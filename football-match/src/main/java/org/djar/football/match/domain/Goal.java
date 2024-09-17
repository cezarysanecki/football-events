package org.djar.football.match.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class Goal {

    private final String id;
    private final String matchId;
    private final int minute;
    private final String scorerId;
    private final Team scoredFor;

}
