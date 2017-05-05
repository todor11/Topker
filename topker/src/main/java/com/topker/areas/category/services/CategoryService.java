package com.topker.areas.category.services;

import com.topker.areas.category.entities.Category;
import com.topker.areas.category.models.bindingModels.CategoryCreationModel;
import com.topker.areas.category.models.viewModels.CategoryNameViewModel;

import java.util.List;

public interface CategoryService {

    Category create(CategoryCreationModel categoryCreationModel);

    boolean isCategoryNameTaken(String name);

    List<CategoryNameViewModel> getAllCategories();
}
