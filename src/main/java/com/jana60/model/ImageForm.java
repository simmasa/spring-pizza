package com.jana60.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

public class ImageForm {

    private Integer id;

    private MultipartFile multiData;

    private Pizza pizza;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MultipartFile getMultiData() {
        return multiData;
    }

    public void setMultiData(MultipartFile multiData) {
        this.multiData = multiData;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
}