package ru.stepanovgzh.axon.data.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.stepanovgzh.axon.data.model.types.BikeColour;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Bike
{
    @Id
    private UUID id;
    private UUID renterId;
    private String name;
    private String description;
    private Instant createdAt;
    private boolean rented;
    private double mileage;
    private BikeColour colour;
}
