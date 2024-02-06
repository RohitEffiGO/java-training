package com.learn.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.app.model.Category;
import com.learn.app.model.CoursesCategory;

public interface CoursesCategoryRepository extends JpaRepository<CoursesCategory, Long> {
	List<CoursesCategory> findByCategory(Category category);
}
