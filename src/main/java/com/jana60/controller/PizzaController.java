package com.jana60.controller;

import com.jana60.model.Pizza;
import com.jana60.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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

    @GetMapping("/add")
    public String PizzAdd(Model m) {
    m.addAttribute("pizza" , new Pizza());
    return "formapizza";
    }
    @PostMapping("/add")
    public String PizzaSave(@ModelAttribute("pizza") Pizza pizzadd) {
//        if (br.hasErrors()) {
//        return "formapizza";
//        } else {
            repo.save(pizzadd);
            return "redirect:/";
//        }
    }

}
