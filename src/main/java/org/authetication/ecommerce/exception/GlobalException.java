package org.authetication.ecommerce.exception;

import jakarta.validation.constraints.Null;
import org.authetication.ecommerce.dto.response.ApiBaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiBaseResponse<Null>> invalidPayload(MethodArgumentNotValidException e){
        String errorMessage = e.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();
        return ResponseEntity.ok(new ApiBaseResponse<>(false,errorMessage,null));
    }


    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ApiBaseResponse<Null>> invalidPayload(AuthorizationDeniedException e){

        return ResponseEntity.ok(new ApiBaseResponse<>(false,"you dont have access to use this endpoints 😁",null));
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiBaseResponse<Null>> handelAllException(Exception e){
        System.out.println(e);
        return ResponseEntity.ok(new ApiBaseResponse<>(false,"Something went awrong",null));
    }

}
