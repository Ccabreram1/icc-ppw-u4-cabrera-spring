package ec.edu.ups.icc.fundamentos01.categories.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ec.edu.ups.icc.fundamentos01.categories.dtos.CategoriesResponseDto;
import ec.edu.ups.icc.fundamentos01.categories.dtos.CreateCategoriesDto;
import ec.edu.ups.icc.fundamentos01.categories.entity.CategoryEntity;
import ec.edu.ups.icc.fundamentos01.categories.repository.CategoryRepossitory;
import ec.edu.ups.icc.fundamentos01.exceptions.domain.ConflictException;

@Service
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepossitory repository;

    public CategoryServiceImp(CategoryRepossitory repository) {
        this.repository = repository;
    }

    @Override
    public List<CategoriesResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(entity -> {
                    CategoriesResponseDto dto = new CategoriesResponseDto();
                    dto.id = entity.getId();
                    dto.name = entity.getName();
                    dto.description = entity.getDescription();
                    return dto;
                })
                .toList();
    }

    @Override
    public void save(CreateCategoriesDto dto) {
        // Valida que el nombre no sea nulo
        if (dto.name == null || dto.name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede ser nulo o vacío");
        }

        // Valida que no exista una categoria con el mismo nombre
        repository.findByNameIgnoreCase(dto.name)
                .ifPresent(existingCategory -> {
                    throw new ConflictException("Ya existe esa categoria" + dto.name);
                });

        // Crear la entidad de categoria

        CategoryEntity entity = new CategoryEntity();
        entity.setName(dto.name);
        entity.setDescription(dto.description);

        // Guardar la entidad en la base de datos
        repository.save(entity);
    }

}
