package ch.bbw.obelix.quarry.api;

import ch.bbw.obelix.quarry.api.dto.MenhirDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;
import java.util.UUID;

public interface QuarryApi {

    @GetExchange("/api/menhirs")
    public List<MenhirDto> getAllMenhirs();

    @GetExchange("/api/menhirs/{menhirId}")
    public MenhirDto getMenhirById(@PathVariable("menhirId") UUID menhirId);

    @PostExchange("/api/menhirs")
    public MenhirDto createMenhir(@RequestBody MenhirDto menhirDto);

    @PutExchange("/api/menhirs/{menhirId}")
    public MenhirDto updateMenhir(@PathVariable("menhirId") UUID menhirId, @RequestBody MenhirDto menhirDto);

    @DeleteExchange("/api/menhirs/{menhirId}")
    public void deleteById(@PathVariable("menhirId") UUID menhirId);

}
