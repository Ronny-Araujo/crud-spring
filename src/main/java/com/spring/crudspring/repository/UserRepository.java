package com.spring.crudspring.repository;

import java.util.List;

import com.spring.crudspring.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u from User u where upper(trim(u.name)) like %?1%")
    List<User> findByName(String name);
}
