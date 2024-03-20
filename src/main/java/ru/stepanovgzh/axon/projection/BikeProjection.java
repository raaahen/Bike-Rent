package ru.stepanovgzh.axon.projection;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import ru.stepanovgzh.axon.data.model.Bike;
import ru.stepanovgzh.axon.data.model.EntityMapper;
import ru.stepanovgzh.axon.data.repository.BikeRepository;
import ru.stepanovgzh.axon.data.view.BikeView;
import ru.stepanovgzh.axon.cqrs.bike.event.*;
import ru.stepanovgzh.axon.cqrs.bike.query.AllBikesQuery;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BikeProjection
{
    private final EntityMapper mapper;
    private final BikeRepository repository;

    @EventHandler
    public void on(BikeCreatedEvent event)
    {
        repository.save(mapper.map(event));
    }

    @EventHandler
    public void on(BikeRentedEvent event)
    {
        Bike bike = repository.findById(event.getId())
                .orElseThrow(() -> new EntityNotFoundException("Bike with id %s not found" + event.getId()));
        bike.setRented(true);
        bike.setRenterId(event.getRenterId());
        repository.save(bike);
    }

    @EventHandler
    public void on(BikeReturnedEvent event)
    {
        Bike bike = repository.findById(event.getId())
                .orElseThrow(() -> new EntityNotFoundException("Bike with id %s not found" + event.getId()));
        bike.setRented(false);
        bike.setRenterId(null);
        repository.save(bike);
    }

    @EventHandler
    public void on(BikeUpdatedEvent event)
    {
        Bike bikeFromDb = repository.findById(event.getId())
                .orElseThrow(() -> new EntityNotFoundException("Bike with id %s not found" + event.getId()));
        Bike bikeFromEvent = mapper.map(event);
        Bike result = mapper.merge(bikeFromEvent, bikeFromDb);
        repository.save(result);
    }

    @EventHandler
    public void on(BikeDeletedEvent event)
    {
        repository.deleteById(event.getId());
    }

    @QueryHandler
    public List<BikeView> handle(AllBikesQuery query)
    {
        return repository.findAll().stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }
}
