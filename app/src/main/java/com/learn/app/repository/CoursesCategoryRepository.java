package com.learn.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.app.model.CoursesCategory;

public interface CoursesCategoryRepository extends JpaRepository<CoursesCategory, Long> {

}
