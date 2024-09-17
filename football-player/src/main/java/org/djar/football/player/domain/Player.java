package org.djar.football.player.domain;

public record Player(String id, String name) {

    @Override
    public String toString() {
        return name;
    }
}
