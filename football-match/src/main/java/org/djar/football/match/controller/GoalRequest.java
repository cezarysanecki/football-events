package org.djar.football.match.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class GoalRequest {

    private String id;
    private int minute;
    private String scorerId;
    private LocalDateTime reqTimestamp;

}
