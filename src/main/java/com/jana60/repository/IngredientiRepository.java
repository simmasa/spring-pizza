package com.jana60.repository;

import com.jana60.model.Ingredienti;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredientiRepository extends CrudRepository<Ingredienti, Integer> {

    public List<Ingredienti> findAllByOrderByName();
}