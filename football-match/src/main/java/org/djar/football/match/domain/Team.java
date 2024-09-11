package org.djar.football.match.domain;

public class Team {

    private final String clubId;

    Team(String clubId) {
        this.clubId = clubId;
    }

    public String getClubId() {
        return clubId;
    }

}
