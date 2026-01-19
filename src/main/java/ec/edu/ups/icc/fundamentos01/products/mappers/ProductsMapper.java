package ec.edu.ups.icc.fundamentos01.products.mappers;

import ec.edu.ups.icc.fundamentos01.products.dtos.CreateProductDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.ProductResponseDto;
import ec.edu.ups.icc.fundamentos01.products.models.Product;

public class ProductsMapper {


    public static Product toModel (int id, String name, Double price, String description) {
        return new Product(id, name, price, description);
    }
    /**
     * Convierte un DTO de creación a un modelo Product
     */
    public static Product fromCreateDto(CreateProductDto dto) {
        return new Product(0,dto.name, dto.price, dto.description);
    }

    /**
     * Convierte un Product a un DTO de respuesta
     * @param product Modelo de dominio
     * @return DTO con los datos públicos del producto
     */
    public static ProductResponseDto toResponse(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.id = product.getId();
        dto.name = product.getName();
        dto.price = product.getPrice();
        dto.description = product.getDescription();

        return dto;
    }

}