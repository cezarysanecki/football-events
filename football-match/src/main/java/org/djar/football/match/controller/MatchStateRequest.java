package org.djar.football.match.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MatchStateRequest {

    private String newState;
    private LocalDateTime reqTimestamp;

}
