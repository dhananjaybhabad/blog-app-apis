package com.codewithdb.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithdb.blog.entities.User;

public interface UserRepo extends JpaRepository<User , Integer> {
}
