package ec.edu.ups.icc.fundamentos01.categories.service;



import java.util.List;

import org.springframework.stereotype.Service;

import ec.edu.ups.icc.fundamentos01.categories.dtos.CategoriesResponseDto;
import ec.edu.ups.icc.fundamentos01.categories.dtos.CreateCategoriesDto;

@Service
public interface CategoryService {
    
    List<CategoriesResponseDto> findAll();

    void save(CreateCategoriesDto dto);

}
