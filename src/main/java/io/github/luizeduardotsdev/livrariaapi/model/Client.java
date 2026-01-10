package io.github.luizeduardotsdev.livrariaapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String clientID;

    private String clientSecret;

    private String redirectURI;

    private String scope;

}
