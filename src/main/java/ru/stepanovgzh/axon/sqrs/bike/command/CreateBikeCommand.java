package ru.stepanovgzh.axon.sqrs.bike.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.Instant;
import java.util.UUID;

@Value
public class CreateBikeCommand
{
    @TargetAggregateIdentifier
    UUID id;
    UUID renterId;
    String name;
    String description;
    Instant createdAt;
    boolean rented;
}
