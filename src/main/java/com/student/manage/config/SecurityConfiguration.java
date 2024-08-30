package com.student.manage.config;

import com.student.manage.domain.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * create by GYH on 2023/7/10
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationHandler authenticationHandler = new AuthenticationHandler(redisTemplate);
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/error",
                                "/swagger-ui/*", "/swagger-resources/*", "/v3/api-docs/*", "/webjars/**",
                                "/*.html", "/*.js", "/*.css", "/*.png", "/*.ico", "/lnfraredCamera/**",
                                "/animal/recognition/addAnimal"
                        ).permitAll()
                        .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .successHandler(authenticationHandler)
                        .failureHandler(authenticationHandler)
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(authenticationHandler)
                        .authenticationEntryPoint(authenticationHandler)
                )
                .securityContext(context -> context.securityContextRepository(authenticationHandler))
        ;
        return http.build();
    }

    /**
     * 添加角色继承关系
     *
     * @return RoleHierarchy
     */
    @Bean
    static MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
        var hierarchy = RoleHierarchyImpl.fromHierarchy(Role.SUPER_ADMIN + " > " + Role.ADMIN + "\n" +
                Role.ADMIN + " > " + Role.USER);
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(hierarchy);
        return expressionHandler;
    }

    /**
     * 解决跨域
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
