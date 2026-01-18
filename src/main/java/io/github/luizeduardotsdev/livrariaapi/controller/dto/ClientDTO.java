package io.github.luizeduardotsdev.livrariaapi.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "Client")
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
