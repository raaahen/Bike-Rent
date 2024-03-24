package ru.stepanovgzh.axon.aggregate.upcaster;

import lombok.RequiredArgsConstructor;
import org.axonframework.serialization.SimpleSerializedType;
import org.axonframework.serialization.upcasting.event.SingleEventUpcaster;
import org.axonframework.serialization.upcasting.event.IntermediateEventRepresentation;
import org.dom4j.Element;
import ru.stepanovgzh.axon.cqrs.bike.event.BikeUpdatedEvent;

@RequiredArgsConstructor
public class BikeUpdatedEvent1_0To1_1Upcaster extends SingleEventUpcaster
{
    private static final SimpleSerializedType TARGET_TYPE =
            new SimpleSerializedType(BikeUpdatedEvent.class.getTypeName(), "1.0");

    @Override
    protected boolean canUpcast(IntermediateEventRepresentation intermediateRepresentation)
    {
        return intermediateRepresentation.getType().equals(TARGET_TYPE);
    }

    @Override
    protected IntermediateEventRepresentation doUpcast(IntermediateEventRepresentation intermediateRepresentation)
    {
        return intermediateRepresentation.upcastPayload(
                new SimpleSerializedType(TARGET_TYPE.getName(), "1.1"),
                org.dom4j.Document.class,
                document ->
                {
                    Element colour = document.getRootElement().element("colour");
                    colour.detach();
                    document.getRootElement().addElement("bikeColour").setText(colour.getText());
                    return document;
                }
        );
    }
}
