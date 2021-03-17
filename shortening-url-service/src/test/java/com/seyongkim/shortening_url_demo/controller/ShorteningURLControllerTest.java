package com.seyongkim.shortening_url_demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class ShorteningURLControllerTest extends AbstractControllerTest {

    @Autowired
    private ShorteningURLController shorteningURLController;

    @Override
    protected Object controller() {
        return shorteningURLController;
    }

    @Test
    void getOriginalUrlAndRedirect() throws Exception {

        mockMvc.perform(get("/{shortenedUrl}", "someShortenedUrl"))
                .andExpect(status().isNotFound());

    }

    @Test
    void getNumberOfUrlRequestByUrls() throws Exception {
        String wrongShortenedUrl = "http://localhost:8888/NOT-EXIST-URL";
        String wrongOriginalUrl = "http://NOT-EXIST-URL.com";


        mockMvc.perform(get("/url/count").param("shortenedUrl", wrongShortenedUrl))
                .andExpect(status().isNotFound());

        mockMvc.perform(get("/url/count").param("shortenedUrl", ""))
                .andExpect(status().isNotFound());

        mockMvc.perform(get("/url/count").param("originalUrl", wrongOriginalUrl))
                .andExpect(status().isNotFound());

        mockMvc.perform(get("/url/count").param("originalUrl", ""))
                .andExpect(status().isNotFound());
    }
}