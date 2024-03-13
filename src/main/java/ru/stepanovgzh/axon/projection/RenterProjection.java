package ru.stepanovgzh.axon.projection;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;
import ru.stepanovgzh.axon.data.model.EntityMapper;
import ru.stepanovgzh.axon.data.model.Renter;
import ru.stepanovgzh.axon.data.repository.RenterRepository;
import ru.stepanovgzh.axon.sqrs.renter.event.RenterCreatedEvent;
import ru.stepanovgzh.axon.sqrs.renter.event.RenterDeletedEvent;
import ru.stepanovgzh.axon.sqrs.renter.event.RenterUpdatedEvent;

@Service
@RequiredArgsConstructor
public class RenterProjection
{
    private final EntityMapper mapper;
    private final RenterRepository repository;

    @EventHandler
    public void on(RenterCreatedEvent event)
    {
        repository.save(mapper.map(event));
    }

    @EventHandler
    public void on(RenterUpdatedEvent event)
    {
        Renter renterFromDb = repository.findById(event.getId())
                .orElseThrow(() -> new EntityNotFoundException("Renter with id %s not found" + event.getId()));
        Renter renterFromEvent = mapper.map(event);
        Renter result = mapper.merge(renterFromEvent, renterFromDb);
        repository.save(result);
    }

    @EventHandler
    public void on(RenterDeletedEvent event)
    {
        repository.deleteById(event.getId());
    }
}
