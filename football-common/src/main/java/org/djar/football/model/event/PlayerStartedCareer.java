package org.djar.football.model.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@NoArgsConstructor(force = true)
public class PlayerStartedCareer implements Event {

    private final UUID eventId = UUID.randomUUID();
    private final Instant timestamp = Instant.now();

    private final String playerId;
    private final String name;

    public PlayerStartedCareer(String playerId, String name) {
        this.playerId = Objects.requireNonNull(playerId);
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public String getAggId() {
        return playerId;
    }

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return playerId + "," + name;
    }
}
