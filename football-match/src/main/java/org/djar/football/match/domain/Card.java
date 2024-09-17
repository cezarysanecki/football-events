package org.djar.football.match.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(force = true)
class Card {

    private final String id;
    private final String matchId;
    private final int minute;
    private final String receiverId;
    private final Type type;

    enum Type {
        YELLOW, RED
    }

}
