package com.jana60.repository;

import com.jana60.model.Pizza;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PizzaRepository extends CrudRepository<Pizza , Integer> {
    List<Pizza> findByNameContainsIgnoreCase(String name);
    public Integer countByNameAllIgnoreCase(String name);
}
