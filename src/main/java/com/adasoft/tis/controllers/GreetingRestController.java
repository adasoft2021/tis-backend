package com.adasoft.tis.controllers;

import com.adasoft.tis.domain.Greeting;
import com.adasoft.tis.services.GreetingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/welcome")
@AllArgsConstructor
public class GreetingRestController {
    private GreetingService greetingService;

    @GetMapping("{name}")
    public ResponseEntity<Greeting> welcome(@NotNull @PathVariable("name") final String name) {
        Greeting response = greetingService.getGreeting(name);
        return ResponseEntity.ok(response);
    }
}
