package ru.stepanovgzh.axon.aggregate;

import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import ru.stepanovgzh.axon.sqrs.renter.command.CreateRenterCommand;
import ru.stepanovgzh.axon.sqrs.renter.command.DeleteRenterCommand;
import ru.stepanovgzh.axon.sqrs.renter.command.UpdateRenterCommand;
import ru.stepanovgzh.axon.sqrs.renter.event.RenterCreatedEvent;
import ru.stepanovgzh.axon.sqrs.renter.event.RenterDeletedEvent;
import ru.stepanovgzh.axon.sqrs.renter.event.RenterUpdatedEvent;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class RenterAggregate
{
    private UUID id;
    private String name;
    private String lastName;
    private int age;

    @CommandHandler
    public RenterAggregate(CreateRenterCommand command)
    {
        AggregateLifecycle.apply(new RenterCreatedEvent(
                command.getId(),
                command.getName(),
                command.getLastName(),
                command.getAge()
        ));
    }

    @EventSourcingHandler
    public void on(RenterCreatedEvent event)
    {
        this.id = event.getId();
        this.name = event.getName();
        this.lastName = event.getLastName();
        this.age = event.getAge();
    }

    @CommandHandler
    public void handle(UpdateRenterCommand command)
    {
        AggregateLifecycle.apply(new RenterUpdatedEvent(
                command.getId(),
                command.getName(),
                command.getLastName(),
                command.getAge()
        ));
    }

    @EventSourcingHandler
    public void on(RenterUpdatedEvent event)
    {
        this.name = event.getName();
        this.lastName = event.getLastName();
        this.age = event.getAge();
    }

    @CommandHandler
    public void handle(DeleteRenterCommand command)
    {
        AggregateLifecycle.apply(new RenterDeletedEvent(
                command.getId()
        ));
    }

    @EventSourcingHandler
    public void on(RenterDeletedEvent event)
    {
        AggregateLifecycle.markDeleted();
    }
}
