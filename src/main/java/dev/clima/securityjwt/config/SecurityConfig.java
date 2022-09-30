package dev.clima.securityjwt.config;

import dev.clima.securityjwt.security.JWTFilter;
import dev.clima.securityjwt.security.service.UserDetailServiceImpl;
import dev.clima.securityjwt.service.PathService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private JWTFilter jwtFilter;

    private UserDetailServiceImpl userDetailService;

    private PathService pathService;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(auth -> {
                    auth.antMatchers("/api/auth/**").permitAll();
                    pathService.getAll().forEach(path -> {
                        path.getRoles().forEach(role ->
                                auth.antMatchers(path.getHttpMethod(), path.getName()).hasAuthority(role.getName()));
                        path.getPrivileges().forEach(privilege ->
                                auth.antMatchers(path.getHttpMethod(), path.getName()).hasAuthority(privilege.getName()));
                        path.getAccessRules().forEach(accessRule ->
                                auth.antMatchers(path.getHttpMethod(), path.getName()).access(accessRule.getRule()));
                    });
                    auth.anyRequest().authenticated();
                })
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .userDetailsService(userDetailService)
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                )
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
