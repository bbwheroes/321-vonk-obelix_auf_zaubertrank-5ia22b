package ch.bbw.obelix.basket.service;

import ch.bbw.obelix.quarry.api.QuarryApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;


@Service
public class QuarryWebClientService {

    @Value("${microService.quarryService}")
    private String serviceUrl;

    private final WebClient.Builder builder;

    public QuarryWebClientService(WebClient.Builder builder) {
        this.builder = builder;
    }

    public QuarryApi getApi() {
        WebClient webClient = builder
                .baseUrl(serviceUrl)
                .build();

        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .build();

        return httpServiceProxyFactory.createClient(QuarryApi.class);
    }
}
