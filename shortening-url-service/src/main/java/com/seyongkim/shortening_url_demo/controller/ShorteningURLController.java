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

    @PostMapping("/url")
    public ResponseEntity<UrlDto.Response> createShorteningURL(@RequestBody @Valid UrlDto.Request requestDto) {
        UrlDto.Response response = shorteningURLService.create(requestDto);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{shortenedUrl}")
    public ResponseEntity<HttpStatus> getOriginalUrlAndRedirect(@PathVariable String shortenedUrl) throws NotFoundException {
        UrlDto.Info info = shorteningURLService.count(shortenedUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Location", info.getOriginalUrl());
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    // GET (/{shortenedUrl}/count)
    // 어떤거 기준으로 조회할지 shortenedUrl or originalUrl or 아니면 둘다

}
