package com.provedcode.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.provedcode.user.mapper.UserInfoMapper;
import com.provedcode.user.repo.UserInfoRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    //http://localhost:8080/swagger-ui/index.html
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(c -> c
                .requestMatchers("/actuator/health").permitAll() // for DevOps
                .requestMatchers(antMatcher("/h2/**")).permitAll()
                .requestMatchers(antMatcher("/api/*/talents/**")).permitAll()
                .requestMatchers(antMatcher("/api/*/sponsors/**")).permitAll()
                .requestMatchers(antMatcher("/api/*/login")).permitAll()
                .requestMatchers(antMatcher("/api/*/skills")).permitAll()
                .requestMatchers(antMatcher("/api/*/proofs/**")).permitAll()
                .requestMatchers(antMatcher("/error")).permitAll()
                .requestMatchers(antMatcher("/v3/api-docs/**")).permitAll() // for openAPI
                .requestMatchers(antMatcher("/swagger-ui/**")).permitAll() // for openAPI
                .requestMatchers(antMatcher("/swagger-ui.html")).permitAll() // for openAPI
                .requestMatchers(antMatcher(HttpMethod.GET, "/api/v5/activate")).permitAll()// for email account recovery
                .anyRequest().authenticated()
        );

        http.exceptionHandling(c -> c
                .authenticationEntryPoint((request, response, authException) -> {
                            log.info("Authentication failed {}, message:{}",
                                    describe(request),
                                    authException.getMessage());
                            response.sendError(
                                    HttpStatus.UNAUTHORIZED.value(),
                                    authException.getMessage());
                        }
                )
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                            log.info("Authorization failed {},message: {}",
                                    describe(request),
                                    accessDeniedException.getMessage());
                            response.sendError(HttpStatus.FORBIDDEN.value(),
                                    accessDeniedException.getMessage());
                        }
                )
        );

        http.httpBasic(Customizer.withDefaults());
        http.csrf().disable().headers().disable();
        http.cors();


        http.sessionManagement().sessionCreationPolicy(STATELESS);

        http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .exceptionHandling(c -> c
                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
                );

        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }

    public String describe(HttpServletRequest request) {
        return request.getRequestURI();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public KeyPair keyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    UserDetailsService userDetailsService(
            UserInfoRepository repository,
            UserInfoMapper mapper
    ) {
        return login -> repository.findByLogin(login)
                .map(mapper::toUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(login + " not found"));
    }

    @Bean
    JwtDecoder jwtDecoder(KeyPair keyPair) {
        return NimbusJwtDecoder.withPublicKey((RSAPublicKey) keyPair.getPublic()).build();
    }

    @Bean
    JwtEncoder jwtEncoder(KeyPair keyPair) {
        var jwk = new RSAKey.Builder((RSAPublicKey) keyPair.getPublic()).privateKey(keyPair.getPrivate()).build();
        var jwkSet = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSet);
    }
}