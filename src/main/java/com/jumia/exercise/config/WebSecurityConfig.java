package com.jumia.exercise.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${cors.origin:http://localhost:4200}")
    private String corsOrigin;

    @Value("${cors.enabled:true}")
    private String corsEnabled;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disable CSRF protection everywhere for now
        http.csrf().disable();
        if (Boolean.valueOf(corsEnabled)) {
            http.cors().and().authorizeRequests().anyRequest().permitAll();
        }
    }

    @Bean
    @ConditionalOnProperty(value = "cors.enabled", havingValue = "true", matchIfMissing = false)
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin(corsOrigin);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        config.addExposedHeader("Authorization");
        config.addExposedHeader("Content-Disposition");
        return new CorsFilter(source);
    }
}