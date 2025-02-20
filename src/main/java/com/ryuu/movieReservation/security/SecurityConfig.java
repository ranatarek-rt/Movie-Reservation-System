package com.ryuu.movieReservation.security;
import com.ryuu.movieReservation.service.userdetails.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    CustomUserDetailsService customUserDetailsService;

    @Autowired
    SecurityConfig(CustomUserDetailsService customUserDetailsService){
        this.customUserDetailsService = customUserDetailsService;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/user","/api/showtime/**","/images/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/movie/**").permitAll()

                        .requestMatchers(HttpMethod.GET,"/api/reserve/user/**").hasAnyRole("USER","ADMIN")

                        .requestMatchers(HttpMethod.POST,"/api/movie/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH,"/api/movie/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/movie/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/reserve").hasRole("ADMIN")
                        .requestMatchers("/api/reserve/details","api/reserve/total/revenue").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST,"/api/reserve").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE,"/api/reserve").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(customUserDetailsService);
        return provider;
    }


}
