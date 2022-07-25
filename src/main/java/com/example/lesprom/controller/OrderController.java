package com.example.lesprom.controller;

import com.example.lesprom.entity.Employee;
import com.example.lesprom.entity.Order;
import com.example.lesprom.entity.TimeOfEmployeeOnOrder;
import com.example.lesprom.entity.Workplace;
import com.example.lesprom.service.EmployeeService;
import com.example.lesprom.service.OrderService;
import com.example.lesprom.service.WorkplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final WorkplaceService workplaceService;
    private final EmployeeService employeeService;

    @Autowired
    public OrderController(OrderService orderService,
                           WorkplaceService workplaceService,
                           EmployeeService employeeService) {
        this.orderService = orderService;
        this.workplaceService = workplaceService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("orderList", orderService.list());
        return "order";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("order", orderService.create());
        model.addAttribute("workplaces", workplaceService.list());
        model.addAttribute("selectedWorkplaces", new ArrayList<Workplace>());
        model.addAttribute("employees", employeeService.list());
        model.addAttribute("selectedEmployees", new HashMap<String, Set<Employee>>());
        return "order_edit";
    }

    @GetMapping("{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Order order = orderService.getById(id);
        model.addAttribute("order", order);
        model.addAttribute("workplaces", workplaceService.list());
        model.addAttribute("selectedWorkplaces", order.getWorkplaces());
        Map<String, Set<Employee>> selectedEmployees = new HashMap<>();
        for (Workplace workplace: order.getWorkplaces()) {
            selectedEmployees.put("selectedEmployeesToWorkplaceId" + workplace.getId().toString(), workplace.getEmployees());
        }
        model.addAttribute("selectedEmployees", selectedEmployees);
        return "order_edit";
    }

    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Long id,
                       @ModelAttribute("employee") Order item,
                       @RequestParam(value = "selectedWorkplaces") Set<Workplace> selectedWorkplaces) {

        item.setWorkplaces(selectedWorkplaces);
        orderService.save(id, item);
        return "redirect:/order";
    }

    @PostMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        orderService.delete(id);
        return "redirect:/order";
    }

}
