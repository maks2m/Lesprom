package com.example.lesprom.controller;

import com.example.lesprom.entity.Role;
import com.example.lesprom.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/role")
@PreAuthorize("hasAuthority('ADMIN')")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("roleList", roleService.list());
        return "role";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("roles", roleService.create());
        return "role_edit";
    }

    @GetMapping("{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("roles", roleService.getById(id));
        return "role_edit";
    }

    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Long id,
                       @ModelAttribute("roles") Role item) {
        roleService.save(id, item);
        return "redirect:/role";
    }

    @PostMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        roleService.delete(id);
        return "redirect:/role";
    }

}
