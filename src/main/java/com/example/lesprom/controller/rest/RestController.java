package com.example.lesprom.controller.rest;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

public interface RestController<D> {

    @GetMapping
    Object list(@RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                @RequestParam(name = "sortBy", defaultValue = "id") String sortBy);

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
