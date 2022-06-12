package com.co.ias.birds.commons;

public interface UseCase <Input, Output>{
    Output execute(Input input);
}
