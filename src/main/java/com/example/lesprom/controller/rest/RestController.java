package com.example.lesprom.controller.rest;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface RestController<D> {

    @GetMapping
    List<D> list();

    @GetMapping("{id}")
    D getOne(@PathVariable("id") Long id);

    @PostMapping
    D create(@RequestBody D modelDto);

    @PutMapping("{id}")
    D update(@PathVariable("id") Long id,
             @RequestBody D modelDto);

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") Long id);

}
