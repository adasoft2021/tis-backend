package com.adasoft.tis.config;

import com.adasoft.tis.core.utils.JWTAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.LinkedList;
import java.util.List;

@Configuration
public class WebSecurity implements WebMvcConfigurer {
    private static final List<String> EXCLUDE_PATHS = new LinkedList<>();

    private final JWTAuthorization jwtAuthorization;

    @Autowired
    public WebSecurity(final JWTAuthorization jwtAuthorization) {
        this.jwtAuthorization = jwtAuthorization;

        EXCLUDE_PATHS.add("/swagger-ui.html");
        EXCLUDE_PATHS.add("/swagger-ui/**");
        EXCLUDE_PATHS.add("/v3/**");
        EXCLUDE_PATHS.add("/class-codes");
        EXCLUDE_PATHS.add("/companies");
        EXCLUDE_PATHS.add("/publications/published");
        EXCLUDE_PATHS.add("/semesters/now");
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(jwtAuthorization).excludePathPatterns(EXCLUDE_PATHS);
    }
}
