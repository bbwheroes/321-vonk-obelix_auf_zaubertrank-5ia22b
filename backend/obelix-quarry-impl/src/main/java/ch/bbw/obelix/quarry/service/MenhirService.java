package ch.bbw.obelix.quarry.service;

import ch.bbw.obelix.quarry.repository.MenhirRepository;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class MenhirService {

    public MenhirService(MenhirRepository menhirRepository, MeterRegistry meterRegistry) {
        Gauge.builder("menhir_count", menhirRepository::count)
                .description("A current number of menhirs in the system")
                .register(meterRegistry);
    }
}
