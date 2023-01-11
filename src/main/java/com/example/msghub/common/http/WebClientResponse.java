package com.example.msghub.common.http;

/**
 * WebClient response
 * @param statusCode WebClient response status code
 * @param body WebClient response body
 * @param <T> WebClient response body type
 */
public record WebClientResponse<T>(int statusCode, T body) { }
