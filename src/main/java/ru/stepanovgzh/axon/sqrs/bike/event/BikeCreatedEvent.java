package ru.stepanovgzh.axon.sqrs.bike.event;

import lombok.Value;

import java.time.Instant;
import java.util.UUID;

@Value
public class BikeCreatedEvent
{
    UUID id;
    String name;
    String description;
    Instant createdAt;
}
