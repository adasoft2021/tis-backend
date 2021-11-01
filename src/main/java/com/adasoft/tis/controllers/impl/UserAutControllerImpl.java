package com.adasoft.tis.controllers.impl;

import com.adasoft.tis.controllers.UserAutController;
import com.adasoft.tis.mail.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserAutControllerImpl implements UserAutController {

    @Autowired
    public EmailService emailService;

    @Override
    @PostMapping("/{email}")
    public String createMail(
        @NotNull @PathVariable("email") final String email) {
        emailService.sendSimpleMessage(email,
            "TIS Springboot Mail", "Esta es una prueba de envio de mail desde Springboot");
        return "emails";
    }

    @PostMapping("/{email}/{token}")
    public String check(
        @NotNull @PathVariable("email") final String email,
        @NotNull @PathVariable("token") final String token) {
        return token;
    }
}
