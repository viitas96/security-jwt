package dev.clima.securityjwt.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import dev.clima.securityjwt.security.service.UserDetailServiceImpl;
import dev.clima.securityjwt.security.util.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.*;

@Component
@AllArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private UserDetailServiceImpl userDetailService;

    private JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader =  request.getHeader("Authorization");
        if (authHeader != null
                && !authHeader.isBlank()
                && !authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if ( == null || .isBlank()) {
                response.sendError(SC_BAD_REQUEST, "Invalid JWT Token is Bearer Header");
            } else {
                try {
                    String email = jwtUtil.validateTokenAndRetrieveSubject(token);
                    UserDetails userDetails = userDetailService.loadUserByUsername(email);
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(email,
                                    userDetails.getPassword(),
                                    userDetails.getAuthorities());
                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                } catch (JWTVerificationException exception) {
                    response.sendError(SC_BAD_REQUEST, "Invalid JWT Token");
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
