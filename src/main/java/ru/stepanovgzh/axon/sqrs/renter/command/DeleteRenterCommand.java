package ru.stepanovgzh.axon.sqrs.renter.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class DeleteRenterCommand
{
    @TargetAggregateIdentifier
    UUID id;
}
