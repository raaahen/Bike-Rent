package ru.stepanovgzh.axon.data.model;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.stepanovgzh.axon.data.view.BikeView;
import ru.stepanovgzh.axon.cqrs.bike.event.BikeCreatedEvent;
import ru.stepanovgzh.axon.cqrs.bike.event.BikeUpdatedEvent;
import ru.stepanovgzh.axon.cqrs.renter.event.RenterCreatedEvent;
import ru.stepanovgzh.axon.cqrs.renter.event.RenterUpdatedEvent;
import ru.stepanovgzh.axon.data.view.RenterView;

@Mapper
public interface EntityMapper
{
    Bike map(BikeCreatedEvent event);

    Bike map(BikeUpdatedEvent event);

    Bike merge(Bike bikeFromEvent, @MappingTarget Bike bikeFromDb);

    Renter map(RenterCreatedEvent event);

    Renter map(RenterUpdatedEvent event);

    Renter merge(Renter renterFromEvent, @MappingTarget Renter renterFromDb);

    BikeView map(Bike bike);

    RenterView map(Renter renter);
}
