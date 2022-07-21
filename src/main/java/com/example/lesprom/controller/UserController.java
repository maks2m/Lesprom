package com.example.lesprom.controller;

import com.example.lesprom.service.RoleService;
import com.example.lesprom.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService,
                          RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("userList", userService.list());
        return "user";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("user", userService.create());
        model.addAttribute("roleList", roleService.list());
        return "user_edit";
    }

    @GetMapping("{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roleList", roleService.list());
        return "user_edit";
    }

    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Long id,
                       @RequestParam Map<String, String> mapModel,
                       Model model) {
        if (userService.save(id, mapModel, model)) {
            return "redirect:/user";
        } else {
            return "user_edit";
        }
    }

    @PostMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/user";
    }
}
