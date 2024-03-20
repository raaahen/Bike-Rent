package ru.stepanovgzh.axon.cqrs.bike.event;

import lombok.Value;

import java.util.UUID;

@Value
public class BikeDeletedEvent
{
    UUID id;
}
