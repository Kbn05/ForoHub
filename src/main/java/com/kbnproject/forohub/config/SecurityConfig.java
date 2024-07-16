package com.kbnproject.forohub.config;

import com.kbnproject.forohub.utils.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationProvider AuthenticationProvider;

    @Autowired
    JwtConfig jwtConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(AuthenticationProvider)
                .addFilterBefore(jwtConfig, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authz -> {
                            authz.requestMatchers(HttpMethod.POST, "/user/signup").permitAll();
                            authz.requestMatchers(HttpMethod.POST, "/user/login").permitAll();
                            authz.requestMatchers(HttpMethod.POST, "/topic").hasAuthority(Permission.BASIC_PERMISSION.name());
                            authz.requestMatchers(HttpMethod.GET, "/topic").hasAuthority(Permission.BASIC_PERMISSION.name());
                            authz.requestMatchers(HttpMethod.PUT, "/topic/{topicId}").hasAuthority(Permission.BASIC_PERMISSION.name());
                            authz.requestMatchers(HttpMethod.DELETE, "/topic/{topicId}").hasAuthority(Permission.BASIC_PERMISSION.name());
                            authz.anyRequest().authenticated();
                        }
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
