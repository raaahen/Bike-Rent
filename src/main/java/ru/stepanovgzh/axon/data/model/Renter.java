package ru.stepanovgzh.axon.data.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Renter
{
    @Id
    private UUID id;
    private String name;
    private String lastName;
    private int age;
}
