package com.example.myBoard.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // H2 콘솔 허용
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/h2-console/**", "/articles/**").permitAll()
                                .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                )

                // 최신 방식으로 CSRF 끄기 + H2 콘솔에 iframe 허용
                .csrf(csrf -> csrf
//                        .ignoringRequestMatchers("/h2-console/**", "/articles/**")
                        .ignoringRequestMatchers("/**")
                )
                .headers(AbstractHttpConfigurer::disable // 모든 header 보호 끔 (테스트용. 실무에선 최소로 제한해야 함)
                )

                // form 로그인 기본 제공
                .formLogin(Customizer.withDefaults());

        return http.build();
    }
}
