package ru.stepanovgzh.axon.cqrs.bike.event;

import lombok.Value;
import org.axonframework.serialization.Revision;
import ru.stepanovgzh.axon.data.model.types.BikeColour;

import java.util.UUID;

@Value
@Revision("1.1")
public class BikeUpdatedEvent
{
    UUID id;
    String name;
    String description;
    BikeColour bikeColour;
}
