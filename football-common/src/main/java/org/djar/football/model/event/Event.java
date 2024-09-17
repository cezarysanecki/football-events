package org.djar.football.model.event;

import java.time.Instant;
import java.util.UUID;

public interface Event {

    String getAggId();

    UUID getEventId();

    Instant getTimestamp();
}
