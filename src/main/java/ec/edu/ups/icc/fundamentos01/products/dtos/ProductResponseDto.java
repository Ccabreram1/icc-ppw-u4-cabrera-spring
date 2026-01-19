package ec.edu.ups.icc.fundamentos01.products.dtos;

import java.time.LocalDateTime;
import java.util.List;

import ec.edu.ups.icc.fundamentos01.categories.dtos.CategoriesResponseDto;


public class ProductResponseDto {

    public Long id;
    public String name;
    public Double price;
    public String description;

    // ============== OBJETOS ANIDADOS ==============

    public UserSummaryDto user;
    public List<CategoriesResponseDto> categories;

    // ============== AUDITORÍA ==============

    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    // ============== DTOs INTERNOS ==============

    public static class UserSummaryDto {
        public Long id;
        public String name;
        public String email;
    }

}