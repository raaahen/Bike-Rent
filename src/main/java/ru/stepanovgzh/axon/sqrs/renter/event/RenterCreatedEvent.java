package ru.stepanovgzh.axon.sqrs.renter.event;

import lombok.Value;

import java.util.UUID;

@Value
public class RenterCreatedEvent
{
    UUID id;
    String name;
    String lastName;
    int age;
}
