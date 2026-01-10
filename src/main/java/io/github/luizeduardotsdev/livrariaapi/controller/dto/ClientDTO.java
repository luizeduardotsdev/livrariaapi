package io.github.luizeduardotsdev.livrariaapi.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record ClientDTO(

        @NotBlank(message = "campo obrigatorio")
        String clientId,

        @NotBlank(message = "campo obrigatorio")
        String clientSecret,

        @NotBlank(message = "campo obrigatorio")
        String redirectUri,

        String scope

) {

}
