package com.spring.security;


import com.spring.dao.UserDAO;
import com.spring.security.jwt.JwtAuthenticationFilter;
import com.spring.security.jwt.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserPricipalService userPricipalService ;
    private final UserDAO userDAO;

    @Autowired
    public SecurityConfiguration(UserPricipalService userPricipalService, UserDAO userDAO) {
        this.userPricipalService = userPricipalService;
        this.userDAO = userDAO;
    }




    @Override
    protected void configure(AuthenticationManagerBuilder auth) {

        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http.
                csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().
                addFilter(new JwtAuthenticationFilter(authenticationManager())).
                addFilter(new JwtAuthorizationFilter(authenticationManager(),this.userDAO)).
                authorizeHttpRequests().
                antMatchers(HttpMethod.POST,"login").permitAll().
                antMatchers(HttpMethod.POST,"/logintest").permitAll().
                antMatchers("/api/home").permitAll().
                antMatchers("/api/profile").authenticated().
                antMatchers("/api/user").hasRole("USER").
                antMatchers("/api/management").hasAnyRole("ADMIN", "MANAGEMENT").
                antMatchers("/api/admin/**").hasAuthority("ACCESS_ADMIN").
                anyRequest().authenticated();



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

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
