package com.deliciasvann.delicias_vann.exceptions;

public class ProductFoundException extends RuntimeException {
    public ProductFoundException(){
        super("Produto já existe");
    }
}
