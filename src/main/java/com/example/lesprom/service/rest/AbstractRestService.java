package com.example.lesprom.service.rest;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Абстрактный сервисный класс, реализующий интерфейс стандартного рест сервиса
 * @param <E> дженерик сущности, для которой будет реализовываться интерфейс
 * @param <R> дженерик репозитория сущности, для которой будет реализовываться интерфейс
 */
public abstract class AbstractRestService<E, R> implements RestService<E> {

    protected final R repository;

    @Autowired
    public AbstractRestService(R repository) {
        this.repository = repository;
    }

}
