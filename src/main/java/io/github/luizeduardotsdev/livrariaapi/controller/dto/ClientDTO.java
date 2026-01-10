package io.github.luizeduardotsdev.livrariaapi.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record ClientDTO(

        @NotBlank(message = "campo obrigatorio")
        String clientID,

        @NotBlank(message = "campo obrigatorio")
        String clientSecret,

        @NotBlank(message = "campo obrigatorio")
        String redirectURI,

        String scope

) {

}
