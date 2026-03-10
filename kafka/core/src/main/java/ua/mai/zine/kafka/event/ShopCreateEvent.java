package ua.mai.zine.kafka.event;

import ua.mai.zine.kafka.dto.ShopCreateDto;

public record ShopCreateEvent(
        String shopId,
        String title,
        String address
) {
    public static ShopCreateEvent create(String shopId, ShopCreateDto dto) {
        return new ShopCreateEvent(shopId, dto.title(), dto.address());
    }

}
