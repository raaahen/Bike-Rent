package ru.stepanovgzh.axon.data.input.bike;

import lombok.Value;
import ru.stepanovgzh.axon.data.model.types.BikeColour;

import java.util.UUID;

@Value
public class UpdateBikeInput
{
    UUID bikeId;
    String name;
    String description;
    BikeColour colour;
}
