package com.jana60.controller;

import com.jana60.model.Ingredienti;
import com.jana60.repository.IngredientiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Ingredienti")
public class IngController {

    @Autowired
    public IngredientiRepository repo;

    @GetMapping
    public String Ingredienti(Model m) {
        m.addAttribute("ingre" , repo.findAll());
        m.addAttribute("newIngre", new Ingredienti());
        return "Ingredienti";
    }
    @PostMapping("/add")
    public String addIngre (@ModelAttribute("newIngre") Ingredienti Ingred, Model mod) {
        repo.save(Ingred);
        return "redirect:/Ingredienti";
    }
}
