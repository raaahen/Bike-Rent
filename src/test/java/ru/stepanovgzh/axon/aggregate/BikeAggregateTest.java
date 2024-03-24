package ru.stepanovgzh.axon.aggregate;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.stepanovgzh.axon.cqrs.bike.command.CreateBikeCommand;
import ru.stepanovgzh.axon.cqrs.bike.command.DeleteBikeCommand;
import ru.stepanovgzh.axon.cqrs.bike.command.UpdateBikeCommand;
import ru.stepanovgzh.axon.cqrs.bike.event.BikeCreatedEvent;
import ru.stepanovgzh.axon.cqrs.bike.event.BikeDeletedEvent;
import ru.stepanovgzh.axon.cqrs.bike.event.BikeUpdatedEvent;
import ru.stepanovgzh.axon.data.model.types.BikeColour;
import ru.stepanovgzh.axon.data.repository.BikeRepository;

import java.time.Instant;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class BikeAggregateTest
{
    private static final UUID BIKE_ID = UUID.randomUUID();
    private static final String BIKE_NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final Instant CREATED_AT = Instant.now();
    private static final BikeColour BIKE_COLOUR = BikeColour.BLACK;
    private FixtureConfiguration<BikeAggregate> fixture;

    @Mock
    private BikeRepository bikeRepository;

    @BeforeEach
    public void setUp()
    {
        fixture = new AggregateTestFixture<>(BikeAggregate.class)
                .registerInjectableResource(bikeRepository);
    }

    @Test
    public void createBike()
    {
        fixture.givenNoPriorActivity()
                .when(createBikeCommand())
                .expectSuccessfulHandlerExecution()
                .expectEvents(bikeCreatedEvent());
    }

    @Test
    public void updateBike()
    {
        fixture.given(bikeCreatedEvent())
                .when(updateBikeCommand())
                .expectSuccessfulHandlerExecution()
                .expectEvents(bikeUpdatedEvent());
    }

    @Test
    public void deleteBike()
    {
        fixture.given(bikeCreatedEvent())
                .when(deleteBikeCommand())
                .expectSuccessfulHandlerExecution()
                .expectEvents(bikeDeletedEvent())
                .expectMarkedDeleted();
    }

    private static BikeDeletedEvent bikeDeletedEvent() {
        return new BikeDeletedEvent(BIKE_ID);
    }

    private static DeleteBikeCommand deleteBikeCommand() {
        return new DeleteBikeCommand(BIKE_ID);
    }

    private static BikeUpdatedEvent bikeUpdatedEvent() {
        return new BikeUpdatedEvent(
                BIKE_ID,
                BIKE_NAME,
                DESCRIPTION,
                BIKE_COLOUR
        );
    }

    private static UpdateBikeCommand updateBikeCommand() {
        return new UpdateBikeCommand(
                BIKE_ID,
                BIKE_NAME,
                DESCRIPTION,
                BIKE_COLOUR
        );
    }

    private static CreateBikeCommand createBikeCommand() {
        return new CreateBikeCommand(
                BIKE_ID,
                BIKE_NAME,
                DESCRIPTION,
                CREATED_AT
        );
    }

    private static BikeCreatedEvent bikeCreatedEvent() {
        return new BikeCreatedEvent(
                BIKE_ID,
                BIKE_NAME,
                DESCRIPTION,
                CREATED_AT
        );
    }
}
