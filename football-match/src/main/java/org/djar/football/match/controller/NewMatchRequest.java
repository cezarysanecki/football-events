package org.djar.football.match.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class NewMatchRequest {

    private String id;
    private String seasonId;
    private LocalDateTime matchDate;
    private String homeClubId;
    private String awayClubId;
    private LocalDateTime reqTimestamp;

}
