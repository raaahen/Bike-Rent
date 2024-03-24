package ru.stepanovgzh.axon.data.view;


import lombok.Value;

import java.time.Instant;
import java.util.UUID;

@Value
public class BikeView
{
    UUID id;
    UUID renterId;
    String name;
    String description;
    Instant createdAt;
    boolean rented;
    double mileage;
}
