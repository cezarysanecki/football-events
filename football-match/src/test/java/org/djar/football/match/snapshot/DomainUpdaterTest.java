package org.djar.football.match.snapshot;

import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.djar.football.match.domain.Match;
import org.djar.football.match.domain.Player;
import org.djar.football.match.repo.SeasonRepository;
import org.djar.football.model.event.CardReceived;
import org.djar.football.model.event.GoalScored;
import org.djar.football.model.event.MatchFinished;
import org.djar.football.model.event.MatchScheduled;
import org.djar.football.model.event.MatchStarted;
import org.djar.football.model.event.PlayerStartedCareer;
import org.djar.football.test.StreamsTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DomainUpdaterTest {

  private StreamsTester tester;

  @Before
  public void setUp() throws Exception {
    tester = new StreamsTester(getClass().getName());

    Topology topology = new Topology();
    new DomainUpdater(new SeasonRepository()).init(topology);

    tester.setUp(topology);
  }

  @Test
  public void test() throws Exception {
    tester.sendEvents(getClass().getResource("player-started-career.json"), PlayerStartedCareer.class);
    tester.sendEvents(getClass().getResource("match-scheduled.json"), MatchScheduled.class);
    tester.sendEvents(getClass().getResource("match-started.json"), MatchStarted.class);
    tester.sendEvents(getClass().getResource("goal-scored.json"), GoalScored.class);
    tester.sendEvents(getClass().getResource("card-received.json"), CardReceived.class);
    tester.sendEvents(getClass().getResource("match-finished.json"), MatchFinished.class);

    ReadOnlyKeyValueStore<String, Match> matchStore = tester.getStore(DomainUpdater.MATCH_STORE);
    ReadOnlyKeyValueStore<String, Player> playerStore = tester.getStore(DomainUpdater.PLAYER_STORE);

    assertThat(tester.count(playerStore)).isEqualTo(4);
    assertThat(playerStore.get("3").getName()).isEqualTo("Lewis McGugan");

    assertThat(tester.count(matchStore)).isEqualTo(4);
    Match match1 = matchStore.get("1");
    Match match2 = matchStore.get("2");
    Match match3 = matchStore.get("3");
    Match match4 = matchStore.get("4");

    assertThat(match1.getState()).isEqualTo(Match.State.FINISHED);
    assertThat(match2.getState()).isEqualTo(Match.State.STARTED);
    assertThat(match3.getState()).isEqualTo(Match.State.STARTED);
    assertThat(match4.getState()).isEqualTo(Match.State.SCHEDULED);

    assertThat(match1.getHomeGoals().size()).isEqualTo(1);
    assertThat(match1.getAwayGoals().size()).isEqualTo(1);
    assertThat(match2.getHomeGoals().size()).isEqualTo(1);
    assertThat(match2.getAwayGoals().size()).isEqualTo(0);
    assertThat(match3.getHomeGoals().size()).isEqualTo(3);
    assertThat(match3.getAwayGoals().size()).isEqualTo(1);
    assertThat(match4.getHomeGoals().size()).isEqualTo(0);
    assertThat(match4.getAwayGoals().size()).isEqualTo(0);

    assertThat(match1.getCards().size()).isEqualTo(1);
    assertThat(match2.getCards().size()).isEqualTo(1);
  }

  @After
  public void tearDown() throws Exception {
    tester.close();
  }
}
