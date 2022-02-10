package com.spring.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().
                withUser("amro")
                    .password(passwordEncoder().encode("Amro123#"))
                    .roles("MANAGEMENT")
                    .authorities("ACCESS_2").
                and().
                withUser("israa")
                    .password(passwordEncoder().encode("Israa123#"))
                    .roles("USER")
                    .authorities("ACCESS_3").
                and().
                withUser("hassan")
                    .password(passwordEncoder().encode("Hassan123#"))
                    .roles("ADMIN")
                    .authorities("ACCESS_1");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests().
                //anyRequest().authenticated().
                antMatchers("/api/home").permitAll().
                antMatchers("/api/profile").authenticated().
                antMatchers("/api/management").hasAnyRole("ADMIN","MANAGEMENT").
                antMatchers("/api/admin/**").hasAuthority("ACCESS_1").
                and().
                httpBasic();
    }
@Bean
    PasswordEncoder passwordEncoder ()
    {
        return new BCryptPasswordEncoder();
    }
}
