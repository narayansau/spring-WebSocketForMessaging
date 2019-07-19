package com.example.ActuatorWithWebSocketForMessaging.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Stats{
    private long count;
    private long completed;
}
