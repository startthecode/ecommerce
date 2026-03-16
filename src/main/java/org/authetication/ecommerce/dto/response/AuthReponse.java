package org.authetication.ecommerce.dto.response;

public record AuthReponse(
    String accessToken,
    String refreshToken,
    String username
) 
  {

}
