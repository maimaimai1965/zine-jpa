package ua.mai.zine.kafka.producer.service;

import ua.mai.zine.kafka.dto.ShopCreateDto;

import java.util.concurrent.ExecutionException;


public interface ShopService {

    String createShop(ShopCreateDto shopCreateDto) throws ExecutionException, InterruptedException;

}
