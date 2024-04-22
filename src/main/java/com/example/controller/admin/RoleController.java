package com.example.controller.admin;

import com.example.dto.RoleDto;
import com.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RoleController {
    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/admin/role")
    public String getAllRoles(Model model) {
        List<RoleDto> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "admin/page/role-page";
    }
}
