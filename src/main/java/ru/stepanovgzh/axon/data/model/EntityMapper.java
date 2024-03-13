package ru.stepanovgzh.axon.data.model;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.stepanovgzh.axon.sqrs.bike.event.BikeCreatedEvent;
import ru.stepanovgzh.axon.sqrs.bike.event.BikeUpdatedEvent;
import ru.stepanovgzh.axon.sqrs.renter.event.RenterCreatedEvent;
import ru.stepanovgzh.axon.sqrs.renter.event.RenterUpdatedEvent;

@Mapper
public interface EntityMapper
{
    Bike map(BikeCreatedEvent event);

    Bike map(BikeUpdatedEvent event);

    Bike merge(Bike bikeFromEvent, @MappingTarget Bike bikeFromDb);

    Renter map(RenterCreatedEvent event);

    Renter map(RenterUpdatedEvent event);

    Renter merge(Renter renterFromEvent, @MappingTarget Renter renterFromDb);
}
