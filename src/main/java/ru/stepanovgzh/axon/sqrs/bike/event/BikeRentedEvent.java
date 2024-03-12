package ru.stepanovgzh.axon.sqrs.bike.event;

import lombok.Value;

import java.time.Instant;
import java.util.UUID;

@Value
public class BikeRentedEvent
{
    UUID id;
    UUID renterId;
}
