package com.example.tarea.repository;

import com.example.tarea.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long>{


    List<User> findByIdIn(List<Long> asList);
    List<User> findAll();
    long deleteById(long id);

}
