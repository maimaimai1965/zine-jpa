package ua.mai.zine.jpa.boot.service;

import org.springframework.stereotype.Service;
import ua.mai.zine.jpa.boot.controller.CreateProductDto;


public interface ProductService {

    String createProduct(CreateProductDto createProductDto);
}
