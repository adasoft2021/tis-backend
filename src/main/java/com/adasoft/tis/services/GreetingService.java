package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.*;
import com.adasoft.tis.domain.Greeting;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GreetingService {
    private static final String BASE_MESSAGE = "Bienvenido %s a la API de TIS hecho por AdaSoft!";

    public Greeting getGreeting(final String name) {
        long id = (long) (Math.random() * Long.MAX_VALUE);
        if (id % 10 == 0) {
            throw new DefaultTisDomainException(
                HttpStatus.UNAUTHORIZED,
                "Usted no está autorizado a recibir el mensaje.");
        }
        if (id % 6 == 0) {
            throw new PreConditionException("Se necesita requisitos previos.");
        }
        if (id % 3 == 0) {
            throw new UnableToDeleteEntityException(Greeting.class, id);
        }
        if (id % 8 == 0) {
            throw new EntityNotFoundException(Greeting.class, id);
        }
        if (id % 5 == 0) {
            throw new EntityAlreadyExistException(Greeting.class, id);
        }
        Greeting greeting = new Greeting();
        greeting.setId(id);
        greeting.setMessage(String.format(BASE_MESSAGE, name));
        return greeting;
    }
}
