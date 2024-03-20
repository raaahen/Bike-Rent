package ru.stepanovgzh.axon.controller;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;
import ru.stepanovgzh.axon.cqrs.renter.AllRentersQuery;
import ru.stepanovgzh.axon.data.input.renter.CreateRenterInput;
import ru.stepanovgzh.axon.data.input.renter.DeleteRenterInput;
import ru.stepanovgzh.axon.data.input.renter.UpdateRenterInput;
import ru.stepanovgzh.axon.data.view.RenterView;
import ru.stepanovgzh.axon.cqrs.renter.command.CreateRenterCommand;
import ru.stepanovgzh.axon.cqrs.renter.command.DeleteRenterCommand;
import ru.stepanovgzh.axon.cqrs.renter.command.UpdateRenterCommand;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/v1/renter")
@RequiredArgsConstructor
public class RenterController
{
    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

    @PostMapping("/create")
    public CompletableFuture<UUID> createRenter(@RequestBody CreateRenterInput input)
    {
        return commandGateway.send(new CreateRenterCommand(
                UUID.randomUUID(),
                input.getName(),
                input.getLastName(),
                input.getAge()
        ));
    }

    @PostMapping("/update")
    public CompletableFuture<UUID> updateRenter(@RequestBody UpdateRenterInput input)
    {
        return commandGateway.send(new UpdateRenterCommand(
                input.getId(),
                input.getName(),
                input.getLastName(),
                input.getAge()
        ));
    }

    @DeleteMapping
    public CompletableFuture<Void> deleteRenter(@RequestBody DeleteRenterInput input)
    {
        return commandGateway.send(new DeleteRenterCommand(
                input.getId()
        ));
    }

    @GetMapping
    public CompletableFuture<List<RenterView>> renters()
    {
        return queryGateway.query(new AllRentersQuery(), ResponseTypes.multipleInstancesOf(RenterView.class));
    }
}
