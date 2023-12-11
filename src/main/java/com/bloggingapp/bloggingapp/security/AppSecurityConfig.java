package com.bloggingapp.bloggingapp.security;


import com.bloggingapp.bloggingapp.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JWTService jwtService;
    private final UsersService usersService;

    @Autowired
    public AppSecurityConfig(JWTService jwtService, UsersService usersService) {
        this.jwtService = jwtService;
        this.usersService = usersService;
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JWTAuthenticationFilter(jwtAuthenticationManager());
    }

    @Bean
    public JWTAuthenticationManager jwtAuthenticationManager() {
        return new JWTAuthenticationManager(jwtService, usersService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        configureUrlAccess(http);
        http.addFilterBefore(jwtAuthenticationFilter(), AnonymousAuthenticationFilter.class);
    }

    private void configureUrlAccess(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users", "/users/login","/articles", "/articles/**").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/articles", "/articles/**").permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout();
    }
}
