package com.adasoft.tis.services;

import com.adasoft.tis.domain.Greeting;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GreetingService {
    private static final String BASE_MESSAGE = "Bienvenido %s a la API de TIS hecho por AdaSoft!";

    public Greeting getGreeting(final String name) {
        Greeting greeting = new Greeting();
        greeting.setId((long) (Math.random() * Long.MAX_VALUE));
        greeting.setMessage(String.format(BASE_MESSAGE, name));
        return greeting;
    }
}
