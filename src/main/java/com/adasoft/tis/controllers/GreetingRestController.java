package com.adasoft.tis.controllers;

import com.adasoft.tis.core.exceptions.EntityConflictException;
import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.domain.Greeting;
import com.adasoft.tis.services.GreetingService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@OpenAPIDefinition
@RestController
@RequestMapping("/welcome")
@AllArgsConstructor
public class GreetingRestController {
    private GreetingService greetingService;

    @GetMapping("{name}")
    public ResponseEntity<Greeting> welcome(@NotNull @PathVariable("name") final String name) {
        throw new EntityConflictException(Greeting.class,1);
        //Greeting response = greetingService.getGreeting(name);
        //return ResponseEntity.ok(response);
    }
}
