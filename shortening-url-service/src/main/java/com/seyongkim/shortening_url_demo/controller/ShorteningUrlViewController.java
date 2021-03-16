package com.seyongkim.shortening_url_demo.controller;

import com.seyongkim.shortening_url_demo.model.dto.UrlDto;
import com.seyongkim.shortening_url_demo.service.ShorteningURLService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ShorteningUrlViewController {
    private final ShorteningURLService shorteningURLService;

    @RequestMapping("/")
    public String greetingSubmit(Model model) {
        return "input";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createShorteningURL(@RequestParam("url") @Valid String url, Model model) {
        UrlDto.Response response = shorteningURLService.create(url);
        model.addAttribute("shortenedUrl", response.getShortenedUrl());
        return "input";
    }
}
