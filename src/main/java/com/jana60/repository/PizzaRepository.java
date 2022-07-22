package com.jana60.repository;

import com.jana60.model.Pizza;
import org.springframework.data.repository.CrudRepository;

public interface PizzaRepository extends CrudRepository<Pizza , Integer> {
    public Integer countByNameAllIgnoreCase(String name);
}
