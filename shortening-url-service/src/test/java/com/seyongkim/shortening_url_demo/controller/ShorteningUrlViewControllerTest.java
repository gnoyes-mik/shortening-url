package com.seyongkim.shortening_url_demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ShorteningUrlViewControllerTest extends AbstractControllerTest {

    @Autowired
    private ShorteningUrlViewController shorteningUrlViewController;

    @Override
    protected Object controller() {
        return shorteningUrlViewController;
    }

    @Test
    void viewInputFormPageTest() throws Exception {
        mockMvc.perform(get("/").contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void createShorteningURLTest() throws Exception {
        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.set("url", "https://www.musinsa.com");
        mockMvc.perform(MockMvcRequestBuilders.post("/")
                .params(requestParam))
                .andExpect(status().isOk())
                .andReturn();
    }

}