package com.example.lesprom.controller;

import com.example.lesprom.entity.Employee;
import com.example.lesprom.entity.Workplace;
import com.example.lesprom.service.EmployeeService;
import com.example.lesprom.service.WorkplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final WorkplaceService workplaceService;

    @Autowired
    public EmployeeController(EmployeeService employeeService,
                              WorkplaceService workplaceService) {
        this.employeeService = employeeService;
        this.workplaceService = workplaceService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("employeeList", employeeService.list());
        return "employee";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("employee", employeeService.create());
        model.addAttribute("workplaces", workplaceService.list());
        model.addAttribute("selectedWorkplaces", new ArrayList<Workplace>());
        return "employee_edit";
    }

    @GetMapping("{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.getById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("workplaces", workplaceService.list());
        model.addAttribute("selectedWorkplaces", employee.getWorkplaces());
        return "employee_edit";
    }

    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Long id,
                       @ModelAttribute("employee") Employee item,
                       @RequestParam(value = "selectedWorkplaces") Set<Workplace> selectedWorkplaces) {
        item.setWorkplaces(selectedWorkplaces);
        employeeService.save(id, item);
        return "redirect:/employee";
    }

    @PostMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        employeeService.delete(id);
        return "redirect:/employee";
    }

}
