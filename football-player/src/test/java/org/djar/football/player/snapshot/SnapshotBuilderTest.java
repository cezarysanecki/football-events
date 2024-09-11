package org.djar.football.player.snapshot;

import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.djar.football.model.event.PlayerStartedCareer;
import org.djar.football.player.domain.Player;
import org.djar.football.test.StreamsTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SnapshotBuilderTest {

  private StreamsTester tester;

  @Before
  public void setUp() throws Exception {
    tester = new StreamsTester(getClass().getName());

    Topology topology = new Topology();
    new DomainUpdater().init(topology);

    tester.setUp(topology);
  }

  @Test
  public void test() throws Exception {
    tester.sendEvents(getClass().getResource("player-started-career.json"), PlayerStartedCareer.class);
    ReadOnlyKeyValueStore<String, Player> playerStore = tester.getStore(DomainUpdater.PLAYER_STORE);

    assertThat(tester.count(playerStore)).isEqualTo(4);
    assertThat(playerStore.get("2").name()).isEqualTo("James Henry");
  }

  @After
  public void tearDown() throws Exception {
    tester.close();
  }
}
