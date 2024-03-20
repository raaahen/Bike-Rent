package ru.stepanovgzh.axon.data.input.bike;

import lombok.Value;

import java.util.UUID;

@Value
public class ReturnBikeInput
{
    UUID bikeId;
    double mileageRidden;
}
