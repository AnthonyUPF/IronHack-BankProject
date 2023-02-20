package com.ironhack.demosecurityjwt.security;

import com.ironhack.demosecurityjwt.filters.CustomAuthenticationFilter;
import com.ironhack.demosecurityjwt.filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * This is the main configuration class for security in the application. It enables web security,
 * sets up the password encoder, and sets up the security filter chain.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    // UserDetailsService is an interface provided by Spring Security that defines a way to retrieve user information
    @Autowired
    private UserDetailsService userDetailsService;

    // Autowired instance of the AuthenticationManagerBuilder
    @Autowired
    private AuthenticationManagerBuilder authManagerBuilder;

    /**
     * Bean definition for PasswordEncoder
     *
     * @return an instance of the DelegatingPasswordEncoder
     */
    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * Bean definition for AuthenticationManager
     *
     * @param authenticationConfiguration the instance of AuthenticationConfiguration
     * @return an instance of the AuthenticationManager
     * @throws Exception if there is an issue getting the instance of the AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Bean definition for SecurityFilterChain
     *
     * @param http the instance of HttpSecurity
     * @return an instance of the SecurityFilterChain
     * @throws Exception if there is an issue building the SecurityFilterChain
     */
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CustomAuthenticationFilter instance created
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authManagerBuilder.getOrBuild());
        // set the URL that the filter should process
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        // disable CSRF protection
        http.csrf().disable();
        // set the session creation policy to stateless
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        // set up authorization for different request matchers and user roles
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/api/login/**").permitAll()
                .requestMatchers(GET, "/api/users").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .requestMatchers(POST, "/api/users").hasAnyAuthority("ROLE_ADMIN")

/*
                .requestMatchers(GET,"/api/bankUsers/admins/**").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(PATCH,"/api/bankUsers/admins/**").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(POST,"/api/bankUsers/admins/**").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(PUT,"/api/bankUsers/admins/**").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(DELETE,"/api/bankUsers/admins/**").hasAnyAuthority("ROLE_ADMIN")

                .requestMatchers(GET,"/api/bankUsers/accountHolders/**").hasAnyAuthority("ROLE_ACCOUNT_HOLDER")
                .requestMatchers( PATCH,"/api/bankUsers/accountHolders/**").hasAnyAuthority("ROLE_ACCOUNT_HOLDER")
                .requestMatchers(POST,"/api/bankUsers/accountHolders/**").hasAnyAuthority("ROLE_ACCOUNT_HOLDER")
                .requestMatchers(PUT,"/api/bankUsers/accountHolders/**").hasAnyAuthority("ROLE_ACCOUNT_HOLDER")
                .requestMatchers(DELETE,"/api/bankUsers/accountHolders/**").hasAnyAuthority("ROLE_ACCOUNT_HOLDER")

                .requestMatchers(GET,"/api/bankUsers/thirdParties/**").hasAnyAuthority("ROLE_THIRD_PARTY")
                .requestMatchers(PATCH,"/api/bankUsers/thirdParties/**").hasAnyAuthority("ROLE_THIRD_PARTY")
                .requestMatchers(POST,"/api/bankUsers/thirdParties/**").hasAnyAuthority("ROLE_THIRD_PARTY")
                .requestMatchers(PUT,"/api/bankUsers/thirdParties/**").hasAnyAuthority("ROLE_THIRD_PARTY")
                .requestMatchers(DELETE,"/api/bankUsers/thirdParties/**").hasAnyAuthority("ROLE_THIRD_PARTY")
*/
                .requestMatchers("/api/accounts/**").permitAll()
                .requestMatchers("/api/bankUsers/**").permitAll()






                .anyRequest().permitAll());
        // add the custom authentication filter to the http security object
        http.addFilter(customAuthenticationFilter);
        // Add the custom authorization filter before the standard authentication filter.
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        // Build the security filter chain to be returned.
        return http.build();
    }
}
