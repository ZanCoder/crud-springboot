package com.example.controller.admin;

import com.example.Entity.Employee;
import com.example.dto.EmployeeDto;
import com.example.dto.RoleDto;
import com.example.service.EmployeeService;
import com.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {
    private EmployeeService employeeService;
    private RoleService roleService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, RoleService roleService) {
        this.employeeService = employeeService;
        this.roleService = roleService;
    }

    @GetMapping("/admin/employee")
    public String employeeList(Model model) {
        List<EmployeeDto> employeeDtos = employeeService.getAllEmployees();
        List<RoleDto> roleDtos = roleService.getAllRoles();
        model.addAttribute("employees", employeeDtos);
        model.addAttribute("roles", roleDtos);
        return "admin/page/employee-page";
    }

    @GetMapping("/admin/employee/create")
    public String employeeFormCreate(Model model) {
        EmployeeDto employeeDto = new EmployeeDto();
        List<RoleDto> roleDtos = roleService.getAllRoles();
        model.addAttribute("employee", employeeDto);
        model.addAttribute("roles", roleDtos);
        return "admin/create/create-employee";
    }

    @PostMapping("/admin/employee/create")
    public String employeeCreate(@ModelAttribute("employee") EmployeeDto employee) {
        employeeService.save(employee);
        return "redirect:/admin/employee";
    }

    @GetMapping("/admin/employee/update")
    public String employeeFormUpdate(@RequestParam("id") Long id, Model model) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(id);
        List<RoleDto> roleDtos = roleService.getAllRoles();
        model.addAttribute("employee", employeeDto);
        model.addAttribute("roles", roleDtos);
        return "admin/update/update-employee";
    }

    @PostMapping("/admin/employee/update")
    public String employeeUpdate(@ModelAttribute("employee") EmployeeDto employee) {
        employeeService.update(employee);
        return "redirect:/admin/employee";
    }

    @GetMapping("/admin/employee/delete")
    public String employeeDelete(@RequestParam("id") Long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/admin/employee";
    }

    @GetMapping("/admin/employee/search")
    public String findEmployees(@RequestParam("name") String name, Model model) {
        List<Employee> employees = employeeService.findByName(name);
        model.addAttribute("employees", employees);
        return "admin/page/employee-page";
    }
}
