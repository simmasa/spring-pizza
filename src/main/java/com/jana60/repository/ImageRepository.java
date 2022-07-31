package com.jana60.repository;

import com.jana60.model.Image;
import com.jana60.model.Pizza;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image, Integer> {

    public List<Image> findByPizza(Pizza pizza);

}