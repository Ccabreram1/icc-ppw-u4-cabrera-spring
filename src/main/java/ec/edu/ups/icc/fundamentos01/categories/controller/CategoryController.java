package ec.edu.ups.icc.fundamentos01.categories.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.ups.icc.fundamentos01.categories.dtos.CategoriesResponseDto;
import ec.edu.ups.icc.fundamentos01.categories.dtos.CreateCategoriesDto;
import ec.edu.ups.icc.fundamentos01.categories.service.CategoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategoriesResponseDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody CreateCategoriesDto dto) {
        service.save(dto);// Guardar la categoría
        return ResponseEntity.ok("Categoria creada");
    }

}
