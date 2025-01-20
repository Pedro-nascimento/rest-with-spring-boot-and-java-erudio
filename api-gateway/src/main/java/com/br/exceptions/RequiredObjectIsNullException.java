package com.br.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullException extends RuntimeException{

    public RequiredObjectIsNullException(String ex){
        super(ex);
    }

    public RequiredObjectIsNullException(){
        super("não é possível consistir um objeto nulo.");
    }
}
