package com.gateway.config;


import javax.servlet.http.HttpServletResponse;

import com.gateway.jwt.JwtTokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtConfig jwtConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                //.antMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/oglas/**").permitAll()
        		.antMatchers("/oglas/create").permitAll()
        		.antMatchers("/oglas/update").permitAll()
        		.antMatchers("/oglas/delete/**").permitAll()
        		.antMatchers("/oglas/verify/**").permitAll()
        		.antMatchers("/oglas/termin/createTermin").permitAll()
        		.antMatchers("/oglas/termin/update").permitAll()
        		.antMatchers("/oglas/termin/delete/**").permitAll()
        		.antMatchers("/oglas/vozilo/novoVozilo").permitAll()
        		.antMatchers("/oglas/vozilo/**").permitAll()
                .anyRequest().authenticated();

    }

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }

}