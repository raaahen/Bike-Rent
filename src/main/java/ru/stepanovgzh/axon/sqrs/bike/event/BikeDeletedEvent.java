package ru.stepanovgzh.axon.sqrs.bike.event;

import lombok.Value;

import java.util.UUID;

@Value
public class BikeDeletedEvent
{
    UUID id;
}
