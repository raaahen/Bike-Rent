package ru.stepanovgzh.axon.sqrs.bike.event;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class BikeUpdatedEvent
{
    UUID id;
    String name;
    String description;
}
