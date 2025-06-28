package com.ecommerceJava.ecommerce.services.exeptions;

public class ResourceNotFoundException extends RuntimeException { // usar o RuntimeExeption nao precisa usar Try Catch
    public ResourceNotFoundException(String msg) {
        super(msg);
    }

}
