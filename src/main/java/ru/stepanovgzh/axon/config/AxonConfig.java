package ru.stepanovgzh.axon.config;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.correlation.CorrelationDataProvider;
import org.axonframework.messaging.correlation.MessageOriginProvider;
import org.axonframework.messaging.correlation.MultiCorrelationDataProvider;
import org.axonframework.serialization.upcasting.event.EventUpcasterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.stepanovgzh.axon.aggregate.upcaster.BikeUpdatedEvent1_0To1_1Upcaster;
import ru.stepanovgzh.axon.handler.RequestContextFillingFilter;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class AxonConfig
{
    private final RequestContextFillingFilter.RequestContext requestContext;

    @Bean
    public EventUpcasterChain eventUpcasterChain()
    {
        return new EventUpcasterChain(
                new BikeUpdatedEvent1_0To1_1Upcaster()
        );
    }

    @Bean
    public CorrelationDataProvider customCorrelationDataProviders() {
        return new MultiCorrelationDataProvider<CommandMessage<?>>(
                Arrays.asList(
                        new CustomCorrelationDataProvider(requestContext),
                        new MessageOriginProvider()
                )
        );
    }
}
