package org.djar.football.model.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@NoArgsConstructor(force = true)
public class GoalScored implements Event {

    private final UUID eventId = UUID.randomUUID();
    private final Instant timestamp = Instant.now();

    private final String goalId;
    private final String matchId;
    private final int minute;
    private final String scorerId;
    private final String scoredFor;

    public GoalScored(String goalId, String matchId, int minute, String scorerId, String scoredFor) {
        this.goalId = Objects.requireNonNull(goalId, "Null goal id");
        this.matchId = Objects.requireNonNull(matchId, "Null match id");
        this.minute = minute;
        this.scorerId = scorerId;
        this.scoredFor = scoredFor;
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
        return matchId + "," + minute + "m," + scorerId + "," + scoredFor;
    }
}
