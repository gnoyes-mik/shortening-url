package com.seyongkim.shortening_url_demo.controller;

import com.seyongkim.shortening_url_demo.model.dto.UrlDto;
import com.seyongkim.shortening_url_demo.service.ShorteningURLService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ShorteningURLController {

    private final ShorteningURLService shorteningURLService;

    @PostMapping(value = "/url")
    public ResponseEntity<UrlDto.Response> createShorteningURL(@RequestBody @Valid UrlDto.Request requestDto) {
        UrlDto.Response response = shorteningURLService.create(requestDto);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{shortenedUrl}")
    public ResponseEntity<HttpStatus> getOriginalUrlAndRedirect(@PathVariable String shortenedUrl) throws NotFoundException {
        UrlDto.Info response = shorteningURLService.count(shortenedUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Location", response.getOriginalUrl());
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping(value = "/url/count", params = {"shortenedUrl"})
    public ResponseEntity<UrlDto.Info> getNumberOfUrlRequestByShortenedUrl(@RequestParam String shortenedUrl) throws NotFoundException {
        UrlDto.Info response = shorteningURLService.getNumberOfUrlRequestByShortenedUrl(shortenedUrl);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/url/count", params = {"originalUrl"})
    public ResponseEntity<UrlDto.Info> getNumberOfUrlRequestByOriginalUrl(@RequestParam String originalUrl) throws NotFoundException {
        UrlDto.Info response = shorteningURLService.getNumberOfUrlRequestByOriginalUrl(originalUrl);

        return ResponseEntity.ok(response);
    }

}
