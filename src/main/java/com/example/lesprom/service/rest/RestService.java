package com.example.lesprom.service.rest;

import java.util.List;

public interface RestService<E> {

    List<E> list();
    E getById(Long id);
    E create(E item);
    E update(Long id, E item);
    void delete(Long id);

}
