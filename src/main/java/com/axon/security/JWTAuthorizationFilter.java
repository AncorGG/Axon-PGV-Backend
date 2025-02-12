package com.axon.security;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.axon.model.User;
import com.axon.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private final String SECRET = "mySecretKey";
    
    private final UserRepository userRepository;
    
    public JWTAuthorizationFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        
        String username = request.getParameter("username");
        
        System.out.print("username recibido: " + username);
        
        if (username != null) {
        	/*TEST*/
        	if ("testAdmin".equals(username)) {
                System.out.println("Usuario de prueba detectado, acceso permitido.");
                setUpSpringAuthenticationForTestUser(username);
                chain.doFilter(request, response);
                return;
            }
        	
        	Optional<User> user = userRepository.findByUsername(username);
        	
        	if (user.isPresent() && user.get().getToken() != null) {
                String token = user.get().getToken();
                System.out.println("Token recibido: " + token);
                try {
                    Claims claims = validateToken(token);
                    setUpSpringAuthentication(claims);
                    chain.doFilter(request, response);
                    return;
                } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
                	if (e instanceof ExpiredJwtException) {
                		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                		response.setContentType("aplication/json");
                        response.getWriter().write("{\"error\": \"Token expired\"}");
                        return;
                	} else {
                		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                		response.setContentType("aplication/json");
                        response.getWriter().write("{\"error\": \"Invalid JWT\"}");
                        return;
                	}
                }
            }
        }

        SecurityContextHolder.clearContext();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("aplication/json");
        response.getWriter().write("{\"error\": \"Invalid or missing Token\"}");
        return;
    }

    private Claims validateToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    private void setUpSpringAuthentication(Claims claims) {
        @SuppressWarnings("unchecked")
        List<String> authorities = (List<String>) claims.get("authorities");

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                claims.getSubject(),
                null,
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
    
    private void setUpSpringAuthenticationForTestUser(String username) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                username,
                null,
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN")) // Se asigna el rol de administrador al usuario de prueba
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
