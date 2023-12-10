package com.bloggingapp.bloggingapp.security;


import com.bloggingapp.bloggingapp.users.UsersService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    private JWTService jwtService;
    private UsersService usersService;
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    public AppSecurityConfig(JWTService jwtService, UsersService usersService, JWTAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtService = jwtService;
        this.usersService = usersService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception{
        return  new JWTAuthenticationFilter( new JWTAuthenticationManager(jwtService,usersService));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users","/users/login").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout();

        http.addFilterBefore(jwtAuthenticationFilter, AnonymousAuthenticationFilter.class);
    }


}
