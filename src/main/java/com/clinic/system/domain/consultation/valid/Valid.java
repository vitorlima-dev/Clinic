package com.clinic.system.domain.consultation.valid;

import com.clinic.system.infra.exception.CustomException;

public abstract class Valid<T>{
    public int validate(T data){
        if (violated(data)){
            throw new CustomException(msgError(data));
        }
        System.out.println("exited code 0");
        return 0;
    }
    public abstract boolean violated(T data);
    public abstract String msgError(T data);
}
