package org.djar.football.model.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@NoArgsConstructor(force = true)
public class MatchScheduled implements Event {

    private final UUID eventId = UUID.randomUUID();
    private final Instant timestamp = Instant.now();

    private final String matchId;
    private final String seasonId;
    private final LocalDateTime date;
    private final String homeClubId;
    private final String awayClubId;

    public MatchScheduled(String matchId, String seasonId, LocalDateTime date, String homeClubId, String awayClubId) {
        this.matchId = Objects.requireNonNull(matchId);
        this.seasonId = seasonId;
        this.date = date;
        this.homeClubId = homeClubId;
        this.awayClubId = awayClubId;
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
        return matchId + "," + seasonId + "," + homeClubId + " vs " + awayClubId;
    }
}
