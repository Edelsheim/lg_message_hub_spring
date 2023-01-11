package com.example.msghub.common.http;

import com.example.msghub.common.enumeration.HttpMethod;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class WebClientService {
    /**
     * Http header content-type: application/json
     * @param token bearer token
     * @return org.springframework.http.HttpHeaders
     */
    public HttpHeaders makeApplicationJsonBearerHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.ALL));
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (token != null)
            headers.setBearerAuth(token);
        return headers;
    }

    /**
     * Http header content-type: multipart/form-data
     * @param token bearer token
     * @return org.springframework.http.HttpHeaders
     */
    public HttpHeaders makeMultipartFormDataBearerHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.ALL));
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        if (token != null)
            headers.setBearerAuth(token);
        return headers;
    }

    /**
     * WebClient send
     * @param method GET/POST/POST_MULTIPART/PUT
     * @param url API URL (http)
     * @param uri API URI (/xxx/yyy)
     * @param headers org.springframework.http.HttpHeaders
     * @param body String body data
     * @return String type response body
     */
    public WebClientResponse<String> send(HttpMethod method, @NotNull String url, @NotNull String uri, @NotNull HttpHeaders headers, String body) {
        try {
            switch (method) {
                case HTTP_GET -> {
                    return this.get(url, uri, headers);
                }
                case HTTP_POST -> {
                    return this.post(url, uri, headers, body);
                }
                case HTTP_PUT -> {
                    return this.put(url, uri, headers, body);
                }
            }
            return new WebClientResponse<>(400, null);
        } catch (WebClientResponseException e) {
            return new WebClientResponse<>(e.getStatusCode().value(), e.getResponseBodyAsString(StandardCharsets.UTF_8));
        }
    }

    /**
     * WebClient send
     * @param method GET/POST/POST_MULTIPART/PUT
     * @param url API URL (http)
     * @param uri API URI (/xxx/yyy)
     * @param headers org.springframework.http.HttpHeaders
     * @param body String body data
     * @return String type response body
     */
    public WebClientResponse<String> send(String method, @NotNull String url, @NotNull String uri, @NotNull HttpHeaders headers, String body) {
        method = method.toUpperCase();
        switch (method) {
            case "GET" -> {
                return this.send(HttpMethod.HTTP_GET, url, uri, headers, body);
            }
            case "POST" -> {
                return this.send(HttpMethod.HTTP_POST, url, uri, headers, body);
            }
            case "PUT" -> {
                return this.send(HttpMethod.HTTP_PUT, url, uri, headers, body);
            }
        }
        return new WebClientResponse<>(400, null);
    }

    /**
     * Make WebClient
     * @param url API URL (http)
     * @param headers org.springframework.http.HttpHeaders
     * @return WebClient
     */
    private WebClient makeWebClient(@NotNull String url, @NotNull HttpHeaders headers) {
        return WebClient.builder()
                .baseUrl(url)
                .defaultHeaders(httpHeaders -> headers.forEach((key, values) -> values.forEach((value) -> httpHeaders.set(key, value))))
                .build();
    }

    /**
     * POST send
     * @param url API URL (http)
     * @param uri API URI (/xxx/yyy)
     * @param headers org.springframework.http.HttpHeaders
     * @param body String body data
     * @return String type response body
     */
    private WebClientResponse<String> post(@NotNull String url, @NotNull String uri, @NotNull HttpHeaders headers, String body) {
        WebClient webClient = this.makeWebClient(url, headers);
        ResponseEntity<String> response_entity;
        if (body == null) {
            response_entity = webClient.post()
                    .uri(uri)
                    .retrieve()
                    .toEntity(String.class)
                    .block();
        }
        else if (headers.getContentType() == MediaType.MULTIPART_FORM_DATA) {
            MultipartBodyBuilder builder = new MultipartBodyBuilder();
            /*
            TODO
            */
            response_entity = webClient.put()
                    .uri(uri)
                    .body(BodyInserters.fromMultipartData((builder.build())))
                    .retrieve()
                    .toEntity(String.class)
                    .block();
        }
        else {
            response_entity = webClient.post()
                    .uri(uri)
                    .body(Mono.just(body), String.class)
                    .retrieve()
                    .toEntity(String.class)
                    .block();
        }

        if (response_entity != null)
            return new WebClientResponse<>(response_entity.getStatusCode().value(), response_entity.getBody());
        else
            return new WebClientResponse<>(400, null);
    }

    /**
     * PUT send
     * @param url API URL (http)
     * @param uri API URI (/xxx/yyy)
     * @param headers org.springframework.http.HttpHeaders
     * @param body String body data
     * @return String type response body
     */
    private WebClientResponse<String> put(@NotNull String url, @NotNull String uri, @NotNull HttpHeaders headers, String body) {
        org.springframework.web.reactive.function.client.WebClient webClient = this.makeWebClient(url, headers);
        ResponseEntity<String> response_entity;
        if (body == null) {
            response_entity = webClient.put()
                    .uri(uri)
                    .retrieve()
                    .toEntity(String.class)
                    .block();
        }
        else {
            response_entity = webClient.put()
                    .uri(uri)
                    .body(Mono.just(body), String.class)
                    .retrieve()
                    .toEntity(String.class)
                    .block();
        }

        if (response_entity != null)
            return new WebClientResponse<>(response_entity.getStatusCode().value(), response_entity.getBody());
        else
            return new WebClientResponse<>(400, null);
    }

    /**
     * GET send
     * @param url API URL (http)
     * @param uri API URI (/xxx/yyy)
     * @param headers org.springframework.http.HttpHeaders
     * @return String type response body
     */
    private WebClientResponse<String> get(@NotNull String url, @NotNull String uri, @NotNull HttpHeaders headers) {
        org.springframework.web.reactive.function.client.WebClient webClient = this.makeWebClient(url, headers);
        ResponseEntity<String> response_entity = webClient.get()
                                                          .uri(uri)
                                                          .retrieve()
                                                          .toEntity(String.class)
                                                          .block();
        if (response_entity != null)
            return new WebClientResponse<>(response_entity.getStatusCode().value(), response_entity.getBody());
        else
            return new WebClientResponse<>(400, null);
    }
}
