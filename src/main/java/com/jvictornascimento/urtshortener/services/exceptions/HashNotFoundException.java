package com.jvictornascimento.urtshortener.services.exceptions;

public class HashNotFoundException extends RuntimeException{
    public HashNotFoundException(String hash){
        super("ShortCode: " + hash + " Not found");
    }
}

