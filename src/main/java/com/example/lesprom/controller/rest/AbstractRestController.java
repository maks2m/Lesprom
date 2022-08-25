package com.example.lesprom.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractRestController<E, S> implements RestController<E> {

    protected final S service;

    @Autowired
    protected AbstractRestController(S service) {
        this.service = service;
    }
}
