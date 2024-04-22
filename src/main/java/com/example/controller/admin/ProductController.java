package com.example.controller.admin;

import com.example.Entity.Product;
import com.example.dto.CategoryDto;
import com.example.dto.ProductDto;
import com.example.service.CategoryService;
import com.example.service.ProductService;
import com.example.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/admin/product")
    public String productList(Model model) {
        return productListByPage(model, 1, "");
    }

    @GetMapping("/admin/product/page/{pageNum}")
    public String productListByPage(Model model, @PathVariable int pageNum,
                                                @RequestParam(value = "name", defaultValue = "") String name) {
        Pageable pageable = PageRequest.of(pageNum - 1, ProductServiceImpl.PRODUCT_PER_PAGE);

        Page<ProductDto> productDtos = productService.findByName(name, pageable);
        List<ProductDto> productDtoList = productDtos.getContent();
        List<CategoryDto> categories = categoryService.getAllCategories();

        model.addAttribute("products", productDtoList);
        model.addAttribute("categories", categories);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", productDtos.getTotalPages());
        model.addAttribute("keyword", name);
        return "admin/page/product-page";
    }

    @GetMapping("/admin/product/create")
    public String createFormProduct(Model model) {
        List<CategoryDto> categories = categoryService.getAllCategories();
        ProductDto productDto = new ProductDto();
        model.addAttribute("categories", categories);
        model.addAttribute("products", productDto);
        return "admin/create/create-product";
    }

    @PostMapping("/admin/product/create")
    public String createProduct(@ModelAttribute("product") ProductDto product, RedirectAttributes redirectAttributes) {
        productService.save(product);
        redirectAttributes.addFlashAttribute("message", "Product created successfully");
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/update")
    public String updateFormProduct(@RequestParam("id") Long id, Model model) {
            ProductDto productDto = productService.getProductById(id);
            List<CategoryDto> categories = categoryService.getAllCategories();
            model.addAttribute("product", productDto);
            model.addAttribute("categories", categories);
        return "admin/update/update-product";
    }

    @PostMapping("/admin/product/update")
    public String updateProduct(@ModelAttribute("product") ProductDto product, RedirectAttributes redirectAttributes) {
        productService.update(product);
        redirectAttributes.addFlashAttribute("message", "Product updated successfully!");
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/delete")
    public String deleteProduct(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        productService.deleteProductById(id);
        redirectAttributes.addFlashAttribute("message", "Product deleted successfully!");
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/sort-asc")
    public String getProductListSortedByPriceAsc(Model model) {
        List<Product> products = productService.getAllProductsSortedByPriceAsc();
        model.addAttribute("products", products);
        return "admin/page/product-page";
    }

    @GetMapping("/admin/product/sort-desc")
    public String getProductListSortedByPriceDesc(Model model) {
        List<Product> products = productService.getAllProductsSortedByPriceDesc();
        model.addAttribute("products", products);
        return "admin/page/product-page";
    }
}
