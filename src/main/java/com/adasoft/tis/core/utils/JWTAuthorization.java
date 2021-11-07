package com.adasoft.tis.core.utils;

import com.adasoft.tis.core.exceptions.DefaultTisDomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JWTAuthorization implements HandlerInterceptor {
    private final JWTProvider jwtProvider;

    @Autowired
    public JWTAuthorization(final JWTProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public boolean preHandle(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final Object handler)
        throws DefaultTisDomainException {
        String token = request.getHeader("X-Token");
        try {
            if (token == null) {
                throw new Exception("");
            }
            Long decryptedUserId = jwtProvider.decryptUserId(token);
            request.setAttribute("userId", decryptedUserId);
            return true;
        } catch (final Exception ignored) {
            throw new DefaultTisDomainException(
                HttpStatus.UNAUTHORIZED,
                "Usted no tiene autorización para realizar esta acción.");
        }
    }
}
