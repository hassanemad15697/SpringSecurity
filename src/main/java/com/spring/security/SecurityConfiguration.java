package com.spring.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserPricipalService userPricipalService ;

    @Autowired
    public SecurityConfiguration(UserPricipalService userPricipalService){
        this.userPricipalService = userPricipalService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests().
                //anyRequest().authenticated().
                antMatchers("/api/home").permitAll().
                antMatchers("/api/profile").authenticated().
                antMatchers("/api/management").hasAnyRole("ADMIN","MANAGEMENT").
                antMatchers("/api/admin/**").hasAuthority("ACCESS_ADMIN").
                and().
                formLogin().
                loginPage("/api/login");
    }
    @Bean
    PasswordEncoder passwordEncoder ()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider  daoAuthenticationProvider()
    {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userPricipalService);
        return daoAuthenticationProvider;
    }
}
