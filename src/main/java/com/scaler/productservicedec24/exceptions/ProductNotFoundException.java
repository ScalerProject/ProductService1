package com.scaler.productservicedec24.exceptions;

public class ProductNotFoundException extends RuntimeException {
    private final Long productId;

    public ProductNotFoundException(String message, Long productId) {
        super(message);
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}
