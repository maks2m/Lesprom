package com.example.lesprom.controller;

import com.example.lesprom.entity.Baguette;
import com.example.lesprom.service.BaguetteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/baguette")
public class BaguetteController {

    private final BaguetteService baguetteService;

    @Autowired
    public BaguetteController(BaguetteService baguetteService) {
        this.baguetteService = baguetteService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("baguetteList", baguetteService.list());
        return "baguette";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("baguette", baguetteService.create());
        return "baguette_edit";
    }

    @GetMapping("{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("baguette", baguetteService.getById(id));
        return "baguette_edit";
    }

    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Long id,
                       @ModelAttribute("baguette") Baguette item) {
        baguetteService.save(id, item);
        return "redirect:/baguette";
    }

    @PostMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        baguetteService.delete(id);
        return "redirect:/baguette";
    }

}
