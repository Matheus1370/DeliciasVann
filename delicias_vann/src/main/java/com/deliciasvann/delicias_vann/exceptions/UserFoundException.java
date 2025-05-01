package com.deliciasvann.delicias_vann.exceptions;


public class UserFoundException extends RuntimeException {
    public UserFoundException(){
        super("Usuário já existe");
    }
}
