package com.seyongkim.shortening_url_demo.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class Base52Test {


    @Test
    void encode() {
        long keySize_1 = 1;                         // 1
        long keySize_2 = (long) Math.pow(52, 2);    // 2,704
        long keySize_4 = (long) Math.pow(52, 4);    // 7,311,616
        long keySize_8 = (long) Math.pow(52, 8);    // 53,459,728,531,456

        assertTrue(Base52.encode(keySize_1).length() <= 8);
        assertTrue(Base52.encode(keySize_2).length() <= 8);
        assertTrue(Base52.encode(keySize_4).length() <= 8);
        assertTrue(Base52.encode(keySize_8).length() <= 8);
    }
}