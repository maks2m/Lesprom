package com.example.lesprom.controller.rest;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

public interface RestController<D> {

    @GetMapping
    Object list(@RequestParam(defaultValue = "0") Integer pageNo,
                 @RequestParam(defaultValue = "10") Integer pageSize,
                 @RequestParam(defaultValue = "id") String sortBy);

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
