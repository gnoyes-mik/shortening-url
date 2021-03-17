package com.seyongkim.shortening_url_demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// @SpringBootTest
class ShorteningUrlViewControllerTest extends AbstractControllerTest {

    @Autowired
    private ShorteningURLController shorteningURLController;

    @Override
    protected Object controller() {
        return shorteningURLController;
    }

    // @Test
    void viewInputFormPageTest() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // @Test
    void createShorteningURLTest() throws Exception {
        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.set("url", "https://www.musinsa.com");
        mockMvc.perform(MockMvcRequestBuilders.post("/")
                .params(requestParam))
                .andReturn();
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.model().attribute("shortenedUrl", "Test"));

    }

}