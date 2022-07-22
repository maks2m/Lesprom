package com.example.lesprom.controller;

import com.example.lesprom.entity.Workplace;
import com.example.lesprom.service.WorkplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/workplace")
public class WorkplaceController {

    private final WorkplaceService workplaceService;

    @Autowired
    public WorkplaceController(WorkplaceService workplaceService) {
        this.workplaceService = workplaceService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("workplaceList", workplaceService.list());
        return "workplace";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("workplace", workplaceService.create());
        return "workplace_edit";
    }

    @GetMapping("{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("workplace", workplaceService.getById(id));
        return "workplace_edit";
    }

    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Long id,
                       @ModelAttribute("workplace") Workplace item) {
        workplaceService.save(id, item);
        return "redirect:/workplace";
    }

    @PostMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        workplaceService.delete(id);
        return "redirect:/workplace";
    }

}
