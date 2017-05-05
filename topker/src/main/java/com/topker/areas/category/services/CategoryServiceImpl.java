package com.topker.areas.category.services;

import com.topker.areas.category.entities.Category;
import com.topker.areas.category.models.bindingModels.CategoryCreationModel;
import com.topker.areas.category.models.viewModels.CategoryNameViewModel;
import com.topker.areas.category.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper modelMapper;

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(CategoryCreationModel categoryCreationModel) {
        Category category = this.modelMapper.map(categoryCreationModel, Category.class);
        return this.categoryRepository.save(category);
    }

    @Override
    public boolean isCategoryNameTaken(String name) {
        Category category = this.categoryRepository.findOneByName(name);
        if (category == null) {
            return false;
        }

        return true;
    }

    @Override
    public List<CategoryNameViewModel> getAllCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryNameViewModel> resultCategories = new ArrayList<>();
        for (Category category : categories) {
            CategoryNameViewModel categoryNameViewModel = this.modelMapper.map(category, CategoryNameViewModel.class);
            resultCategories.add(categoryNameViewModel);
        }

        return resultCategories;
    }
}
