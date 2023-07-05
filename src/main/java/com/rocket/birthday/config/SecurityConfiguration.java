package com.rocket.birthday.config;

import com.rocket.birthday.common.oauth2.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration

    private final CustomOAuth2UserService oAuth2UserService;
    private final OAuth2SuccessHandler successHandler;
    private final TokenService tokenService;


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
        .httpBasic(basic -> basic.disable()) // restapi, enable basic authentication
        .csrf(csrf -> csrf.disable()) // post, put
        .sessionManagement(sessionManagement -> {
          sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        })
        .authorizeHttpRequests((authorizeHttpRequests) ->
            authorizeHttpRequests
                .requestMatchers("/token/**").permitAll()
                .anyRequest().authenticated()
        )
        .addFilterBefore(new JwtExceptionFilter(), OAuth2LoginAuthenticationFilter.class)
        .oauth2Login(oauth2 -> {
          oauth2.loginPage("/token/expired").permitAll()
        })
        .successHandler(successHandler)
        .userInfoEndpoint().userService(oAuth2UserService);

      http.addFilterBefore( (new JWTAuthFilter(tokenService)) );


    return http.build();

  }
}
