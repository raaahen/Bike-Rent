package ru.stepanovgzh.axon.data.view;

import lombok.Value;

import java.util.UUID;

@Value
public class RenterView
{
    UUID id;
    String name;
    String lastName;
    int age;
}
