package com.example.controller.admin;

import com.example.Entity.Category;
import com.example.dto.CategoryDto;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/admin/category")
    public String getAllCategories(Model model) {
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "/admin/page/category-page";
    }

    @GetMapping("/admin/category/create")
    public String createCategoryForm(Model model) {
        CategoryDto categoryDto = new CategoryDto();
        model.addAttribute("category", categoryDto);
        return "/admin/create/create-category";
    }

    @PostMapping("/admin/category/create")
    public String createCategory(@ModelAttribute("category") CategoryDto category) {
        categoryService.save(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/admin/category/update")
    public String categoryUpdateForm(@RequestParam("id") Long id, Model model) {
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        model.addAttribute("category", categoryDto);
        return "/admin/update/update-category";
    }

    @PostMapping("/admin/category/update")
    public String categoryUpdate(@ModelAttribute("category") CategoryDto category) {
        categoryService.updateCategory(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/admin/category/delete")
    public String deleteCategory(@RequestParam("id") Long id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/admin/category";
    }

    @GetMapping("/admin/category/search")
    public String searchCategory(@RequestParam("name") String name, Model model) {
        List<Category> categories = categoryService.findByName(name);
        model.addAttribute("categories", categories);
        return "/admin/page/category-page";
    }
}
