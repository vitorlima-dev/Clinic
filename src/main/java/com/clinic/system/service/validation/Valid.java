package com.clinic.system.service.validation;

import com.clinic.system.infrastructure.handleError.CustomException;
public abstract class Valid<T>{
    public void validate(T data){
        if (violated(data)){
            throw new CustomException(msgError(data));
        }
    }
    public abstract boolean violated(T data);
    public abstract String msgError(T data);
}
