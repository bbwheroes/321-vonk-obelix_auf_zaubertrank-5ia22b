package ch.bbw.obelix.webshop.controller;

import java.util.List;
import java.util.UUID;
import ch.bbw.obelix.quarry.api.dto.MenhirDto;
import ch.bbw.obelix.webshop.service.QuarryWebClientService;
import ch.bbw.obelix.basket.api.dto.BasketDto;
import ch.bbw.obelix.webshop.service.BasketWebClientService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class WebshopController {

    private final QuarryWebClientService quarryClient;
    private final BasketWebClientService basketClient;


    @GetMapping("/api")
    public String welcome() {
        return "Welcome to Obelix's Menhir Shop! The finest menhirs in all of Gaul! Ces Romains sont fous!";
    }

	@PutMapping("/api/basket/offer")
	public BasketDto offer(@RequestBody BasketDto.BasketItem basketItem) {
		return basketClient.getApi().offer(basketItem);
	}

	@DeleteMapping("/api/basket")
	public void leave() {
        basketClient.getApi().leave();
	}

    @GetMapping
    public void getBasket() {
        basketClient.getApi().getBasket();
    }

	@PostMapping("/api/basket/buy/{menhirId}")
	public void exchangeFor(@PathVariable UUID menhirId) {
        try{
            basketClient.getApi().exchangeFor(menhirId);
        }catch(Exception e){
            throw new BadBuyException("Invalid request: " + e.getMessage(), e);
        }
	}

    @GetMapping("/api/menhirs")
    public List<MenhirDto> getAllMenhirs(){
        return quarryClient.getApi().getAllMenhirs();
    }

    @StandardException
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class BadBuyException extends RuntimeException {}
}
