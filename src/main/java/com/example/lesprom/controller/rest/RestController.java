package com.example.lesprom.controller.rest;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface RestController<E> {

    @GetMapping
    List<E> list();

    @GetMapping("{id}")
    E getOne(@PathVariable("id") Long id);

    @PostMapping
    E create(@RequestBody E modelDto);

    @PutMapping("{id}")
    E update(@PathVariable("id") Long id,
             @RequestBody E modelDto);

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") Long id);

}
