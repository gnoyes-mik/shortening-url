package com.seyongkim.shortening_url_demo.controller;

import com.seyongkim.shortening_url_demo.model.dto.UrlDto;
import com.seyongkim.shortening_url_demo.service.ShorteningURLService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;

@Controller
@Validated
@RequiredArgsConstructor
public class ShorteningUrlViewController {
    private final ShorteningURLService shorteningURLService;

    @GetMapping("/")
    public String viewInputFormPage() {
        return "input";
    }

    @PostMapping(value = "/")
    public String createShorteningURL(@RequestParam("url") @NotBlank String url, Model model) {
        UrlDto.Response response = shorteningURLService.create(url);
        model.addAttribute("shortenedUrl", response.getShortenedUrl());
        return "input";
    }
}
