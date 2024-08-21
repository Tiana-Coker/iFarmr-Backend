package org.ifarmr.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        security.csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(
                        requests -> requests
                                .requestMatchers(antMatcher(HttpMethod.GET, "/swagger-ui.html")).permitAll()
                                .requestMatchers(antMatcher(HttpMethod.GET, "/swagger-ui/**")).permitAll()
                                .requestMatchers(antMatcher(HttpMethod.GET, "/v3/api-docs/**")).permitAll()
                                .requestMatchers(antMatcher(HttpMethod.GET, "/swagger-resources/**")).permitAll()
                                .requestMatchers(antMatcher(HttpMethod.POST, "/api/v1/auth/**")).permitAll()
                                .anyRequest().authenticated())
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .authenticationProvider(authenticationProvider)
                .formLogin(form -> form
                        .failureHandler(customAuthenticationFailureHandler));

        return security.build();
    }
}
