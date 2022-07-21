package com.jana60.controller;

import com.jana60.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PizzaController {

    @Autowired
    public PizzaRepository repo;

    @GetMapping
    public String Pizza(Model m) {
        m.addAttribute("pizz", repo.findAll());
        return "Index";
    }
}
