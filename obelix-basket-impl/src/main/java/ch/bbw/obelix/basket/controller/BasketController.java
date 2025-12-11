package ch.bbw.obelix.basket.controller;

import ch.bbw.obelix.basket.api.BasketApi;
import ch.bbw.obelix.basket.api.dto.BasketDto;
import ch.bbw.obelix.basket.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BasketController implements BasketApi {

    private final BasketService basketService;

    @Override
    public BasketDto offer(BasketDto.BasketItem basketItem) {
        return basketService.offer(basketItem);
    }

    @Override
    public void leave() {
        basketService.leave();
    }

    @Override
    public void exchangeFor(UUID menhirId) {
        basketService.exchange(menhirId);
    }
}
