package org.authetication.ecommerce.exception;

import jakarta.validation.constraints.Null;
import org.authetication.ecommerce.dto.response.ApiBaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;

import javax.security.auth.login.CredentialException;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ApiBaseResponse<Null>> handelAuthException(AuthException e){
        return ResponseEntity.ok(new ApiBaseResponse<>(e.getStatus(),e.getMessage(),null));
    }

    @ExceptionHandler({BadCredentialsException.class,CredentialException.class, IllegalArgumentException.class, MethodNotAllowedException.class, GenericException.class, UsernameNotFoundException.class})
    public ResponseEntity<ApiBaseResponse<Void>> handleExceptions(Exception e) {
        return ResponseEntity.ok(
                new ApiBaseResponse<>(false, e.getMessage(), null)
        );
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiBaseResponse<Null>> handelAllException(Exception e){
        return ResponseEntity.ok(new ApiBaseResponse<>(false,"Something went awrong",null));
    }

}
