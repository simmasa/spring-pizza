package com.jana60.controller;

import com.jana60.model.Pizza;
import com.jana60.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

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
    public String PizzaSave(@Valid @ModelAttribute("pizza") Pizza pizzadd, BindingResult br) {
        if (br.hasErrors()) {
        return "formapizza";
        } else {
            repo.save(pizzadd);
            return "redirect:/";
        }
    }
    @GetMapping("/delete/{id}")
    public String PizzaDel (@PathVariable("id") Integer pizid , RedirectAttributes rd) {
        Optional<Pizza> pizDel = repo.findById(pizid);
        if (pizDel.isPresent()) {
            repo.deleteById(pizid);
            rd.addFlashAttribute("deleted","L'elemento è stato eliminato");
            return "redirect:/";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "La pizza " + pizid + " non esiste");
        }

    }
}
