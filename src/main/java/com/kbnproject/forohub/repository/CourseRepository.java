package com.kbnproject.forohub.repository;

import com.kbnproject.forohub.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
