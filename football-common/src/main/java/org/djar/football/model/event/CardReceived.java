package org.djar.football.model.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@NoArgsConstructor(force = true)
public class CardReceived implements Event {

    public enum Type {
        YELLOW, RED
    }

    private final UUID eventId = UUID.randomUUID();
    private final Instant timestamp = Instant.now();

    private final String cardId;
    private final String matchId;
    private final int minute;
    private final String receiverId;
    private final Type type;

    public CardReceived(String cardId, String matchId, int minute, String receiverId, Type type) {
        this.cardId = Objects.requireNonNull(cardId);
        this.matchId = Objects.requireNonNull(matchId);
        this.minute = minute;
        this.receiverId = receiverId;
        this.type = type;
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
        return matchId + "," + minute + "m," + receiverId + "," + type;
    }
}
