package org.green.shortener.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.green.shortener.domain.ShortenedUrlRequest;
import org.green.shortener.domain.entity.UrlInfo;
import org.green.shortener.service.UrlService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("short/url")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping
    private ResponseEntity<?> shortUrl(@RequestBody ShortenedUrlRequest request) {
        var urlInfo = urlService.shortUrl(request);
        return buildResponse(urlInfo);
    }

    @GetMapping("/{id}")
    @SneakyThrows
    private ResponseEntity<?> redirectUrl(@PathVariable String id) {
        var url = urlService.redirectShortened(id);
        return buildRedirect(url.getUrl());
    }

    private ResponseEntity<?> buildResponse(UrlInfo urlInfo) {
        URI uri = buildLocation(urlInfo.getId());
        return ResponseEntity
                .created(uri)
                .build();
    }

    private URI buildLocation(String shortenedUrlId) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(shortenedUrlId)
                .toUri();
    }

    private ResponseEntity<?> buildRedirect(String shortenedUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(shortenedUrl));
        return ResponseEntity
                .status(HttpStatus.PERMANENT_REDIRECT)
                .headers(headers)
                .build();
    }
}
