package org.djar.football.model.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@NoArgsConstructor(force = true)
public class MatchFinished implements Event {

    private final UUID eventId = UUID.randomUUID();
    private final Instant timestamp = Instant.now();

    private final String matchId;

    public MatchFinished(String matchId) {
        this.matchId = Objects.requireNonNull(matchId);
    }

    @Override
    public String getAggId() {
        return matchId;
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
        return matchId;
    }
}
