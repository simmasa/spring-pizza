package com.jana60.controller;

import com.jana60.model.Image;
import com.jana60.model.ImageForm;
import com.jana60.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/egami")
public class egamiController {

    @Autowired
    public ImageService serv;

    @GetMapping("/{pizzaId}")
    public String image(@PathVariable("pizzaId") Integer pizzaId, Model m){

        List<Image> imgList = serv.getImageByPizzaId(pizzaId);
        ImageForm imgf = serv.creaImageForm(pizzaId);

        m.addAttribute("images",imgList);
        m.addAttribute("form", imgf);

        return "egami";
    }

    @PostMapping("/save")
    public String saveImage(@ModelAttribute("imageForm") ImageForm imageForm) {
        try {
            Image savedImage = serv.creaImage(imageForm);
            return "redirect:/egami/" + savedImage.getPizza().getId();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to save image");
        }
    }

    @RequestMapping(value = "/{imageId}/content", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImageContent(@PathVariable("imageId") Integer imageId) {
        byte[] content = serv.getImage(imageId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(content, headers, HttpStatus.OK);
    }
}
