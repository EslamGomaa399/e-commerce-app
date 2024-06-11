package com.egomaa.product.service;

import com.egomaa.product.dto.CategoryDTO;
import com.egomaa.product.entity.Category;
import com.egomaa.product.exception.ResourceNotFoundException;
import com.egomaa.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;


    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = mapper.map(categoryDTO, Category.class);
        return mapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id : " + id));

        return mapper.map(category, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {

        List<CategoryDTO> categoryList = categoryRepository.findAll()
                .stream()
                .map(category -> mapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
        return categoryList;
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));

        mapper.map(categoryDTO, Category.class);

        Category savedCategory = categoryRepository.save(category);

        return mapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Long id) {

        Category category = categoryRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Category not found with id"));

        categoryRepository.delete(category);
    }




}
