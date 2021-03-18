package com.seyongkim.shortening_url_demo.service;

import com.seyongkim.shortening_url_demo.model.dto.UrlDto;
import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class ShorteningURLServiceTest {

    @Autowired
    ShorteningURLService shorteningURLService;


    @BeforeEach
    void setUp() {
        for (int i = 1; i <= 5; i++) {
            shorteningURLService.create("http://exURL" + i + ".com");
        }
    }

    @Test
    void create() {
        UrlDto.Response response = shorteningURLService.create("http://exURL1.com");

        String expected = "http://localhost:8888/C";

        // 동일한 URL이 존재한다면 기존에 등록된 단축 URL 반환
        Assertions.assertEquals(expected, response.getShortenedUrl());
    }

    @Test
    void count() {
        ArrayList<String> errorCase = new ArrayList<>();

        errorCase.add(null);
        errorCase.add("");
        errorCase.add("Not exist URL");

        assertAll(
                () -> Assertions.assertThrows(NotFoundException.class, () -> shorteningURLService.count(errorCase.get(0))),
                () -> Assertions.assertThrows(NotFoundException.class, () -> shorteningURLService.count(errorCase.get(1))),
                () -> Assertions.assertThrows(NotFoundException.class, () -> shorteningURLService.count(errorCase.get(2)))
        );
    }

    @Test
    void getNumberOfUrlRequestByAnyUrl() {
        ArrayList<String> errorCase = new ArrayList<>();

        errorCase.add(null);
        errorCase.add("");
        errorCase.add("Not exist URL");

        assertAll(
                // By ShortenedUrl
                () -> Assertions.assertThrows(NotFoundException.class, () -> shorteningURLService.getNumberOfUrlRequestByShortenedUrl(errorCase.get(0))),
                () -> Assertions.assertThrows(NotFoundException.class, () -> shorteningURLService.getNumberOfUrlRequestByShortenedUrl(errorCase.get(1))),
                () -> Assertions.assertThrows(NotFoundException.class, () -> shorteningURLService.getNumberOfUrlRequestByShortenedUrl(errorCase.get(2))),

                // By OriginalUrl
                () -> Assertions.assertThrows(NotFoundException.class, () -> shorteningURLService.getNumberOfUrlRequestByOriginalUrl(errorCase.get(0))),
                () -> Assertions.assertThrows(NotFoundException.class, () -> shorteningURLService.getNumberOfUrlRequestByOriginalUrl(errorCase.get(1))),
                () -> Assertions.assertThrows(NotFoundException.class, () -> shorteningURLService.getNumberOfUrlRequestByOriginalUrl(errorCase.get(2)))
        );
    }

}