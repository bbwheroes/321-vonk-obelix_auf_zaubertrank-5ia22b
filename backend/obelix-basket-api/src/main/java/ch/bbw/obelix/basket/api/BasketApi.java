package ch.bbw.obelix.basket.api;

import ch.bbw.obelix.basket.api.dto.BasketDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import java.util.UUID;

public interface BasketApi {

    @GetExchange("/api/basket")
    public BasketDto getBasket();

    @PutExchange("/api/basket/offer")
    public BasketDto offer(@RequestBody BasketDto.BasketItem basketItem);

    @DeleteExchange("/api/basket")
    public void leave();

    @PostExchange("/api/basket/buy/{menhirId}")
    public void exchangeFor(@PathVariable UUID menhirId);

}
