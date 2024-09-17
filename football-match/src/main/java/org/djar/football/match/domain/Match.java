package org.djar.football.match.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(force = true)
public class Match {

    public enum State {
        SCHEDULED, STARTED, FINISHED, CANCELLED;
    }

    private final String id;
    private final String leagueId;
    private final LocalDateTime date;
    private final Team homeTeam;
    private final Team awayTeam;
    private State state = State.SCHEDULED;

    private final List<Goal> homeGoals = new ArrayList<>();
    private final List<Goal> awayGoals = new ArrayList<>();
    private final List<Card> cards = new ArrayList<>();

    public void start() {
        if (state != State.SCHEDULED) {
            throw new IllegalStateException("Cannot start " + state + " match");
        }
        state = State.STARTED;
    }

    public void finish() {
        if (state != State.STARTED) {
            throw new IllegalStateException("Cannot finish " + state + " match");
        }
        state = State.FINISHED;
    }

    public void newGoal(String goalId, int minute, String scorerId, String scoredForId) {
        if (scoredForId.equals(homeTeam.getClubId())) {
            homeGoals.add(new Goal(goalId, id, minute, scorerId, homeTeam));
        } else if (scoredForId.equals(awayTeam.getClubId())) {
            awayGoals.add(new Goal(goalId, id, minute, scorerId, awayTeam));
        } else {
            throw new IllegalArgumentException("Invalid team id: " + scoredForId);
        }
    }

    public void newRedCard(String cardId, int minute, String receiveId) {
        Card card = new Card(cardId, id, minute, receiveId, Card.Type.RED);
        cards.add(card);
    }

    public void newYellowCard(String cardId, int minute, String receiveId) {
        Card card = new Card(cardId, id, minute, receiveId, Card.Type.YELLOW);
        cards.add(card);
    }
}
