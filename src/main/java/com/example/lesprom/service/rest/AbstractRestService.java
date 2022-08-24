package com.example.lesprom.service.rest;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractRestService<E, R> implements RestService<E> {

    protected final R repository;

    @Autowired
    public AbstractRestService(R repository) {
        this.repository = repository;
    }

}
