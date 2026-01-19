package ec.edu.ups.icc.fundamentos01.products.mappers;

import java.util.List;

import ec.edu.ups.icc.fundamentos01.categories.dtos.CategoriesResponseDto;
import ec.edu.ups.icc.fundamentos01.categories.models.Category;
import ec.edu.ups.icc.fundamentos01.products.dtos.CreateProductDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.ProductResponseDto;
import ec.edu.ups.icc.fundamentos01.products.models.Product;

public class ProductsMapper {

    private List<Category> categories;

    public static Product toModel(int id, String name, Double price, String description) {
        return new Product(id, name, price, description);
    }

    /**
     * Convierte un DTO de creación a un modelo Product
     */
    public static Product fromCreateDto(CreateProductDto dto) {
        return new Product(0, dto.name, dto.price, dto.description);
    }

    public static Product toEntity(Long id, String name, String description, double price) {
        Product product = new Product(name, price, description);
        product.setId(id);
        return product;
    }

    /**
     * Convierte un Product a un DTO de respuesta
     * 
     * @param product Modelo de dominio
     * @return DTO con los datos públicos del producto
     */
    public static ProductResponseDto toResponse(Product product) {

        ProductResponseDto dto = new ProductResponseDto();

        // ===== DATOS BÁSICOS =====
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());

        // ===== CATEGORÍAS =====
        if (product.getCategory() != null) {
            CategoriesResponseDto catDto = new CategoriesResponseDto();
            catDto.setId(product.getCategory().getId());
            catDto.setName(product.getCategory().getName());
            dto.setCategories(null);
        }

        // ===== USUARIO =====
        if (product.getOwner() != null) {
            ProductResponseDto.UserSummaryDto userDto = new ProductResponseDto.UserSummaryDto();
            userDto.id = product.getOwner().getId();
            userDto.name = product.getOwner().getName();
            userDto.email = product.getOwner().getEmail();
            dto.setUser(userDto);
        }

        return dto;
    }
}
