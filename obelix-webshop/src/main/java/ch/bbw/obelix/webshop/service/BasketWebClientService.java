package ch.bbw.obelix.webshop.service;

import ch.bbw.obelix.basket.api.BasketApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;


@Service
public class BasketWebClientService {

    @Value("${microService.basketService}")
    private String serviceUrl;

    private final WebClient.Builder builder;

    public BasketWebClientService(WebClient.Builder builder) {
        this.builder = builder;
    }

    public BasketApi getApi() {
        WebClient webClient = builder
                .baseUrl(serviceUrl)
                .build();

        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .build();

        return httpServiceProxyFactory.createClient(BasketApi.class);
    }
}
