package ru.stepanovgzh.axon.aggregate;

import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import ru.stepanovgzh.axon.cqrs.bike.command.*;
import ru.stepanovgzh.axon.cqrs.bike.event.*;
import ru.stepanovgzh.axon.data.model.types.BikeColour;
import ru.stepanovgzh.axon.data.repository.BikeRepository;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class BikeAggregate
{
    @AggregateIdentifier
    private UUID id;
    private UUID renterId;
    private String name;
    private String description;
    private Instant createdAt;
    private boolean rented;
    private double mileage;
    private BikeColour colour;

    @CommandHandler
    public BikeAggregate(CreateBikeCommand command)
    {
        AggregateLifecycle.apply(new BikeCreatedEvent(
                command.getId(),
                command.getName(),
                command.getDescription(),
                command.getCreatedAt()
        ));
    }

    @EventSourcingHandler
    public void on (BikeCreatedEvent event)
    {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.createdAt = event.getCreatedAt();
    }

    @CommandHandler
    public void handle(RentBikeCommand command)
    {
        AggregateLifecycle.apply(new BikeRentedEvent(
                command.getId(),
                command.getRenterId()
        ));
    }

    @EventSourcingHandler
    public void on(BikeRentedEvent event)
    {
        this.renterId = event.getRenterId();
        this.rented = true;
    }

    @CommandHandler
    public void handle(ReturnBikeCommand command)
    {
        AggregateLifecycle.apply(new BikeReturnedEvent(
                command.getId(),
                command.getMileage()
        ));
    }

    @EventSourcingHandler
    public void on(BikeReturnedEvent event)
    {
        this.mileage += event.getMileage();
        this.rented = false;
    }

    @CommandHandler
    public void handle(UpdateBikeCommand command, BikeRepository bikeRepository)
    {
        AggregateLifecycle.apply(new BikeUpdatedEvent(
                command.getId(),
                command.getName(),
                command.getDescription(),
                command.getColour()
        ));
    }

    @EventSourcingHandler
    public void on(BikeUpdatedEvent event)
    {
        this.name = event.getName();
        this.description = event.getDescription();
        this.colour = event.getBikeColour();
    }

    @CommandHandler
    public void handle(DeleteBikeCommand command)
    {
        AggregateLifecycle.apply(new BikeDeletedEvent(
                command.getId()
        ));
    }

    @EventSourcingHandler
    public void on(BikeDeletedEvent event)
    {
        AggregateLifecycle.markDeleted();
    }
}
