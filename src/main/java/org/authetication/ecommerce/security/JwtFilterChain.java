package org.authetication.ecommerce.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Null;
import org.authetication.ecommerce.dto.response.ApiBaseResponse;
import org.authetication.ecommerce.exception.AuthException;
import org.authetication.ecommerce.services.imp.UserDetailsImp;
import org.authetication.ecommerce.utils.JwtUtils;
import org.jspecify.annotations.NonNull;
import org.springframework.http.MediaType;
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
    private final ObjectMapper mapper;


    public JwtFilterChain(JwtUtils jwtUtils, UserDetailsImp userDetailsImp,ObjectMapper mapper){
        this.mapper = mapper;
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
        } catch (Exception e){
            sendErrorResponse(response,e);
        }

    }

    private void sendErrorResponse(HttpServletResponse response,Exception e) throws IOException {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        if(e instanceof AuthException b){
            mapper.writeValue(response.getWriter(),new ApiBaseResponse<Null>(b.getStatus(),e.getMessage(),null));
        }else{
            mapper.writeValue(response.getWriter(),new ApiBaseResponse<Null>(false,"Something went wrong",null));
        }
    }

}
