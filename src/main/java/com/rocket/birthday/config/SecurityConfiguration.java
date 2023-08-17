package com.rocket.birthday.config;

import com.rocket.birthday.service.jwt.JwtAuthenticationEntryPoint;
import com.rocket.birthday.service.jwt.JwtTokenProvider;
import com.rocket.birthday.service.jwt.JwtAccessDeniedHandler;
import com.rocket.birthday.config.jwt.JwtSecurityConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
  private final JwtTokenProvider jwtTokenProvider;
  private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @Bean
  public WebSecurityCustomizer configure() {
    return (web) -> web.ignoring()
        .requestMatchers("/h2-console/**");
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration
  ) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
        .formLogin().disable()
        .httpBasic().disable();

    http
        .cors().and()
        .csrf().disable();
    http
        .authorizeHttpRequests()
        .requestMatchers(
            "/api/v1/auth/**",
             "/api/v1/today-messages/**"
        ).permitAll()
        .anyRequest()
        .authenticated();

    http
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


    http
        .exceptionHandling()
        .accessDeniedHandler(jwtAccessDeniedHandler)
        .authenticationEntryPoint(jwtAuthenticationEntryPoint);

    http.apply(new JwtSecurityConfiguration(jwtTokenProvider));

    return http.build();
  }
}
