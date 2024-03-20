package ru.stepanovgzh.axon.cqrs.renter.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class CreateRenterCommand
{
    @TargetAggregateIdentifier
    UUID id;
    String name;
    String lastName;
    int age;
}
