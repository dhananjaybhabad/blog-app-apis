package com.codewithdb.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithdb.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
