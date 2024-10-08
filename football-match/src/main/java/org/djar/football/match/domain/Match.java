package org.djar.football.match.domain;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
  private State state;

  private final List<Goal> homeGoals = new ArrayList<>();
  private final List<Goal> awayGoals = new ArrayList<>();
  private final List<Card> cards = new ArrayList<>();

  Match(String id, String leagueId, LocalDateTime date, Team homeTeam, Team awayTeam) {
    this.id = Objects.requireNonNull(id);
    this.leagueId = Objects.requireNonNull(leagueId);
    this.date = Objects.requireNonNull(date);
    this.homeTeam = Objects.requireNonNull(homeTeam);
    this.awayTeam = Objects.requireNonNull(awayTeam);
    this.state = State.SCHEDULED;
  }

  public String getId() {
    return id;
  }

  public String getLeagueId() {
    return leagueId;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public Team getHomeTeam() {
    return homeTeam;
  }

  public Team getAwayTeam() {
    return awayTeam;
  }

  public State getState() {
    return state;
  }

  public List<Goal> getHomeGoals() {
    return homeGoals;
  }

  public List<Goal> getAwayGoals() {
    return awayGoals;
  }

  public List<Card> getCards() {
    return cards;
  }

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
    Goal goal;

    if (scoredForId.equals(homeTeam.getClubId())) {
      goal = new Goal(goalId, id, minute, scorerId, homeTeam);
      homeGoals.add(goal);
    } else if (scoredForId.equals(awayTeam.getClubId())) {
      goal = new Goal(goalId, id, minute, scorerId, awayTeam);
      awayGoals.add(goal);
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
