package ua.mai.zine.kafka.producer.service;

import ua.mai.zine.kafka.dto.ProductCreateDto;

import java.util.concurrent.ExecutionException;


public interface ProductService {

    String createProduct(ProductCreateDto productCreateDto) throws ExecutionException, InterruptedException;

}
