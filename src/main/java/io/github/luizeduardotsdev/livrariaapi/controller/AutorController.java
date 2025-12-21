package io.github.luizeduardotsdev.livrariaapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @PostMapping
    public ResponseEntity salvar(@RequestBody AutorDTO autor) {


        return new ResponseEntity("autor salvo", HttpStatus.CREATED);
    }

}
