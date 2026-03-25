package org.authetication.ecommerce.exception;

public class ProductException extends RuntimeException {
    public ProductException(String message) {
        super(message);
    }

 public static ProductException productAlreadyExists(String field, String value) {
        return new ProductException("Product already exists with same " + field + ": " + value);
    }

}
