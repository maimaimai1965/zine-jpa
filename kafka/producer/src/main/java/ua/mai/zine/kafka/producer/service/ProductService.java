package ua.mai.zine.kafka.producer.service;

import ua.mai.zine.kafka.producer.dto.CreateProductDto;


public interface ProductService {

    String createProduct(CreateProductDto createProductDto);
}
