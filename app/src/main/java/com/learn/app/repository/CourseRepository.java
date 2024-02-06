package com.learn.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.app.model.Courses;

public interface CourseRepository extends JpaRepository<Courses, Long> {

}
