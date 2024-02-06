package com.learn.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.app.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Optional<Category> findByCategoryType(String category);
}
