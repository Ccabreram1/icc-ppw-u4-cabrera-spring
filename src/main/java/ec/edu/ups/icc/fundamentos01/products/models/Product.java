package ec.edu.ups.icc.fundamentos01.products.models;

import ec.edu.ups.icc.fundamentos01.products.dtos.CreateProductDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.PartialUpdateProductsDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.UpdateProductDto;
import ec.edu.ups.icc.fundamentos01.products.entities.ProductsEntity;
import ec.edu.ups.icc.fundamentos01.users.entities.UserEntity;

public class Product {

    private Long id;
    private String name;
    private Double price;
    private String description;
    private String CreatedAt;

    // Contructor para forzar el uso de factory metodos

    public Product(long id, String name, Double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;

    }

    public Product(String name, Double price, String description) {
        this.validateBusinessRules(name, price, description);
        this.name = name;
        this.price = price;
        this.description = description;
    }

    private void validateBusinessRules(String name, Double price, String description) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto debe tener entre 3 y 150 caracteres.");
        }
        if (price == null || price <= 0) {
            throw new IllegalArgumentException("El precio del producto no puede ser negativo.");
        }
        if (description != null && description.length() > 500) {
            throw new IllegalArgumentException("La descripción del producto no puede exceder los 500 caracteres.");
        }
    }

    public ProductsEntity toEntity(UserEntity owner) {
        ProductsEntity entity = new ProductsEntity();
        if (this.id != null && this.id > 0) {
            entity.setId(this.id);
        }

        entity.setName(this.name);
        entity.setPrice(this.price);
        entity.setDescription(this.description);

        // Asignar relaciones
        entity.setOwner(owner);

        return entity;
    }

    public Product update(UpdateProductDto dto) {
        this.name = dto.name;
        this.price = dto.price;
        this.description = dto.description;

        return this;
    }

    public Product update(PartialUpdateProductsDto dto) {
        this.name = dto.name;
        this.price = dto.price;
        this.description = dto.description;

        return this;
    }

     /**
     * Crea un Product desde un DTO de creación
     */
    public static Product fromDto(CreateProductDto dto) {
        return new Product(dto.name, dto.price, dto.description);
    }
    
    public static Product fromEntity(ProductsEntity entity) {
        Product product = new Product(
                entity.getName(),
                entity.getPrice(),
                entity.getDescription());
        product.id = entity.getId();
        return product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    public Product partialUpdate(PartialUpdateProductsDto dto) {
        if (dto.name != null) {
            this.name = dto.name;
        }
        if (dto.price != null) {
            this.price = dto.price;
        }
        if (dto.description != null) {
            this.description = dto.description;
        }
        return this;
    }

}