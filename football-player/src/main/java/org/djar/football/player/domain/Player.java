package org.djar.football.player.domain;

import java.util.Objects;

public record Player(String id, String name) {

    public Player(String id, String name) {
        this.id = Objects.requireNonNull(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
