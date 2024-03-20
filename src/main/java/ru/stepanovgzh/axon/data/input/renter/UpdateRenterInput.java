package ru.stepanovgzh.axon.data.input.renter;

import lombok.Value;

import java.util.UUID;

@Value
public class UpdateRenterInput
{
    UUID id;
    String name;
    String lastName;
    int age;
}
