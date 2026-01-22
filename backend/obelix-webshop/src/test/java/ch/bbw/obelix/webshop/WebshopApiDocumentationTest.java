package ch.bbw.obelix.webshop;

import ch.bbw.obelix.basket.api.BasketApi;
import ch.bbw.obelix.basket.api.dto.BasketDto;
import ch.bbw.obelix.quarry.api.QuarryApi;
import ch.bbw.obelix.quarry.api.dto.DecorativenessDto;
import ch.bbw.obelix.quarry.api.dto.MenhirDto;
import ch.bbw.obelix.webshop.controller.WebshopController;
import ch.bbw.obelix.webshop.service.BasketWebClientService;
import ch.bbw.obelix.webshop.service.QuarryWebClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;

@WebFluxTest(controllers = WebshopController.class)
@ExtendWith(RestDocumentationExtension.class)
class WebshopApiDocumentationTest {

    @Autowired
    ApplicationContext context;

    @org.springframework.boot.test.mock.mockito.MockBean
    QuarryWebClientService quarryClient;

    @org.springframework.boot.test.mock.mockito.MockBean
    BasketWebClientService basketClient;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        this.webTestClient = WebTestClient.bindToApplicationContext(context)
                .configureClient()
                .filter(documentationConfiguration(restDocumentation))
                .build();

        // Default stubs for the downstream service proxies
        QuarryApi quarryApi = mock(QuarryApi.class);
        when(quarryClient.getApi()).thenReturn(quarryApi);
        when(quarryApi.getAllMenhirs()).thenReturn(List.of(
                new MenhirDto(UUID.fromString("11111111-1111-1111-1111-111111111111"), 12.5, "granite", DecorativenessDto.SIMPLE, "A simple granite menhir")
        ));

        BasketApi basketApi = mock(BasketApi.class);
        when(basketClient.getApi()).thenReturn(basketApi);
        when(basketApi.offer(any())).thenReturn(new BasketDto(List.of(new BasketDto.BasketItem("boar", 2))));
        doNothing().when(basketApi).leave();
        doNothing().when(basketApi).exchangeFor(any());
    }

    @Test
    void welcome() {
        webTestClient.get().uri("/api")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(document("welcome",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ));
    }

    @Test
    void getAllMenhirs() {
        webTestClient.get().uri("/api/menhirs")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("get-all-menhirs",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("[].id").description("UUID des Menhirs"),
                                fieldWithPath("[].weight").description("Gewicht (kg)"),
                                fieldWithPath("[].stoneType").description("Stein-Typ"),
                                fieldWithPath("[].decorativeness").description("Dekorationsgrad"),
                                fieldWithPath("[].description").description("Beschreibung")
                        )
                ));
    }

    @Test
    void basketOffer() {
        webTestClient.put().uri("/api/basket/offer")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new BasketDto.BasketItem("boar", 2))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("basket-offer",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("name").description("Name des Artikels"),
                                fieldWithPath("count").description("Anzahl")
                        ),
                        responseFields(
                                fieldWithPath("items").description("Aktueller Warenkorb"),
                                fieldWithPath("items[].name").description("Name des Artikels"),
                                fieldWithPath("items[].count").description("Anzahl")
                        )
                ));
    }

    @Test
    void basketBuy() {
        UUID menhirId = UUID.fromString("11111111-1111-1111-1111-111111111111");

        webTestClient.post().uri("/api/basket/buy/{menhirId}", menhirId)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("basket-buy",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("menhirId").description("UUID des Menhirs, der gekauft werden soll")
                        )
                ));
    }

    @Test
    void basketLeave() {
        webTestClient.delete().uri("/api/basket")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("basket-leave",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ));
    }
}
