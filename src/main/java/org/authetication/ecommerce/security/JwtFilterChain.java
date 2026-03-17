package org.authetication.ecommerce.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.authetication.ecommerce.services.AuthService;
import org.authetication.ecommerce.services.imp.UserDetailsImp;
import org.authetication.ecommerce.utils.JwtUtils;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilterChain extends  OncePerRequestFilter {
   private final JwtUtils jwtUtils;
    private final UserDetailsImp userDetailsImp;

    public JwtFilterChain(JwtUtils jwtUtils, UserDetailsImp userDetailsImp){
       this.jwtUtils = jwtUtils;
        this.userDetailsImp = userDetailsImp;
    }

    @Override
    protected boolean  shouldNotFilter(HttpServletRequest request){
        String path = request.getServletPath();
        return "/api/auth/signup".equals(path)
                || "/api/auth/login".equals(path)
                || "/api/auth/refresh".equals(path)
                || "/api/testing/unprotected".equals(path)
                || "/api/auth/servertest".equals(path);
    }
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull   HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        try{
         String accessToken = request.getHeader("Authorization").substring(7);
         if(accessToken.isEmpty()){
             filterChain.doFilter(request, response);
             return;
             }

         String username = jwtUtils.decodeToken(JwtUtils.TokenType.ACCESS,accessToken);
         if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
             UserDetails user = userDetailsImp.loadUserByUsername(username);
             UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
             authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
             SecurityContextHolder.getContext().setAuthentication(authentication);
         }
            filterChain.doFilter(request, response);
        }catch (Exception e){
            throw e;
        }

    }
}
