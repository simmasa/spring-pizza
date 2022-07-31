package com.jana60.service;

import com.jana60.model.Image;
import com.jana60.model.ImageForm;
import com.jana60.model.Pizza;
import com.jana60.repository.ImageRepository;
import com.jana60.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    public ImageRepository imageRepository;

    @Autowired
    public PizzaRepository pizzaRepository;

    public List<Image> getImageByPizzaId(Integer pizzaId) {
        Pizza pizza = pizzaRepository.findById(pizzaId).get();
        return imageRepository.findByPizza(pizza);
    }

    public ImageForm creaImageForm(Integer pizzaId){
        Pizza pizza = pizzaRepository.findById(pizzaId).get();
        ImageForm img = new ImageForm();
        img.setPizza(pizza);
        return img;
    }

    public Image creaImage(ImageForm imgf) throws IOException {
        Image img = new Image();
        img.setPizza(imgf.getPizza());

        if (imgf.getMultiData() != null) {
            byte[] serialize = imgf.getMultiData().getBytes();
            img.setImageData(serialize);
        }
        return imageRepository.save(img);
    }

    public byte[] getImage (Integer imageId) {
        Image img = imageRepository.findById(imageId).get();
        return img.getImageData();
    }

    public List<byte[]> getAll() {
        List<Image> list = (List<Image>) imageRepository.findAll();
        List<byte[]> dataList = null;

        for (Image i : list) {
            dataList.add(i.getImageData());
        }
        return dataList;
    }
}
