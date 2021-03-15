package com.seyongkim.shortening_url_demo.model.dto;

import com.seyongkim.shortening_url_demo.model.entity.Url;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


public class UrlDto {

    @Getter
    @NoArgsConstructor
    public static class Request {
        @NotBlank
        String originUrl;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        String shortenedUrl;
    }

    @Getter
    @NoArgsConstructor
    public static class Info {
        private String originalUrl;

        private String shortenedUrl;

        private long count;

        public Info(Url url) {
            this.originalUrl = url.getOriginalUrl();
            this.shortenedUrl = url.getShortenedUrl();
            this.count = url.getCount();
        }
    }

}
