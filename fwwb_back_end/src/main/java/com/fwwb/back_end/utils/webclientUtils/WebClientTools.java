package com.fwwb.back_end.utils.webclientUtils;


import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


/**
 * @program: back_end
 * @description: webclient tool
 * @author: CodingLiOOT
 * @create: 2021-03-06 13:16
 * @version: 1.0
 **/
public class WebClientTools {
    private static WebClient webClient = WebClient.builder()
            .defaultHeader("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDb2RlaW5nTGlPT1QiLCJleHAiOjE2MTUyMTEyNzEsInVzZXJuYW1lIjoiTGp6MTIzaGhoIn0.9d4o0PlXqaGuOc6-OJlLa4pArjdjpwhYptspuaLrsRA")
            .baseUrl("http://127.0.0.1:8090")
            .build();

    public static String test() {
        return webClient
                .get()
                .uri(UrlEnum.TEST.getUrl())
                .retrieve()
                .bodyToMono(String.class).block();
    }

    public static ResultTest testStationList() {
        Mono<ResultTest> mono = webClient.get()
                .uri(UrlEnum.TEST_STATION_LIST.getUrl())
                .retrieve()
                .bodyToMono(ResultTest.class);
        System.out.println(mono.toString());
        return mono.block();
    }
}
