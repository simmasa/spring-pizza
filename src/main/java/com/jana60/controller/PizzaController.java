package com.jana60.controller;

import com.jana60.model.Pizza;
import com.jana60.repository.IngredientiRepository;
import com.jana60.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class PizzaController {

    @Autowired
    public PizzaRepository repo;

    @Autowired
    public IngredientiRepository ingRepo;

    @GetMapping
    public String Pizza(Model m) {
        m.addAttribute("pizz", repo.findAll());
        return "Index2";
    }

    @GetMapping("/add")
    public String PizzAdd(Model m) {
    m.addAttribute("pizza" , new Pizza());
    m.addAttribute("ingre" , ingRepo.findAll());
    return "formapizza";
    }
    @PostMapping("/add")
    public String PizzaSave(@Valid @ModelAttribute("pizza") Pizza pizzadd, BindingResult br) {
        boolean brError= br.hasErrors();
        boolean checkName= true;
        System.out.println(pizzadd.getIngredients());
        if (pizzadd.getId() != null){
            Pizza vecia = repo.findById(pizzadd.getId()).get();
            if (vecia.getName().equals(pizzadd.getName())) {
                checkName=false;
            }
        }
        if (checkName && repo.countByNameAllIgnoreCase(pizzadd.getName())> 0) {
            br.addError(new FieldError("pizza", "name", "Il nome deve essere unico"));
            brError=true;
        }

        if (brError) {
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La pizza " + pizid + " non esiste");
        }
    }
    @GetMapping("/edit/{id}")
    public String PizzaEdit(@PathVariable("id") Integer pizid , Model m){
        Optional<Pizza> pizEdi = repo.findById(pizid);
        if (pizEdi.isPresent()) {
            m.addAttribute("pizza", pizEdi.get());
            m.addAttribute("ingre", ingRepo.findAll());
            return "formapizza";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La pizza " + pizid + " non esiste");
        }
    }

    @GetMapping("/search")
    public String pizzaSearch (@RequestParam("pizzaQuery") String query, Model m) {

        List<Pizza> pizzaSearch = repo.findByNameContainsIgnoreCase(query);
        m.addAttribute("pizz" , pizzaSearch);

        return "Index2";

    }
}
