package com.xy365.core.repository;

import com.xy365.core.model.Category;

public interface CategoryRepository extends XyRepository<Category>{

    Category findCategoryByCode(String code);
}
