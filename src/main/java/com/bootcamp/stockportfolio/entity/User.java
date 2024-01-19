package com.bootcamp.stockportfolio.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Getter
@NoArgsConstructor(force = true)
@Entity
@Table(name = "users")
public class User {
    @Id
    private final UUID id = UUID.randomUUID();
    private final String name;
}
