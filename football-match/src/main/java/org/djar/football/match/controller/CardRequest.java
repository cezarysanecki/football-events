package org.djar.football.match.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CardRequest {

    private String id;
    private int minute;
    private String receiverId;
    private String type;
    private LocalDateTime reqTimestamp;

}
