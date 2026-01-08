package ch.bbw.obelix.quarry.api;

import ch.bbw.obelix.quarry.api.dto.MenhirDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;
import java.util.UUID;

public interface QuarryApi {

    @GetExchange("/api/menhirs")
    public List<MenhirDto> getAllMenhirs();

    @GetExchange("/api/menhirs/{menhirId}")
    public MenhirDto getMenhirById(@PathVariable UUID menhirId);

    @PostExchange("/api/menhirs")
    public MenhirDto createMenhir(@RequestBody MenhirDto menhirDto);

    @DeleteExchange("/api/quarry/{menhirId}")
    public void deleteById(@PathVariable UUID menhirId);

}
