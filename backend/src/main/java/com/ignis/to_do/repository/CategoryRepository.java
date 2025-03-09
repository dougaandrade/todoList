package com.ignis.to_do.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ignis.to_do.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}