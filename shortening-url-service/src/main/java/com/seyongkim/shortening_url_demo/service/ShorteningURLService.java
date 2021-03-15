package com.seyongkim.shortening_url_demo.service;

import com.seyongkim.shortening_url_demo.model.dto.UrlDto;
import com.seyongkim.shortening_url_demo.model.entity.Url;
import com.seyongkim.shortening_url_demo.repository.UrlRepository;
import com.seyongkim.shortening_url_demo.util.Base52;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShorteningURLService {

    private final UrlRepository urlRepository;

    private final String apiURL = "http://localhost/";

    @Transactional
    public UrlDto.Response create(UrlDto.Request requestDto) {
        String originalUrl = requestDto.getOriginUrl();

        Url url = urlRepository.findByOriginalUrl(originalUrl);

        String shortenedUrl;
        if (!isNull(url)) {
            shortenedUrl = apiURL + url.getShortenedUrl();

            return new UrlDto.Response(shortenedUrl);
        }

        shortenedUrl = apiURL + saveNewUrl(originalUrl).getShortenedUrl();

        return new UrlDto.Response(shortenedUrl);
    }

    private Url saveNewUrl(String originalUrl) {
        long keyNumber = urlRepository.findMaxId() + 1L;

        Url url = Url.builder()
                .originalUrl(originalUrl)
                .shortenedUrl(Base52.encode(keyNumber))
                .build();

        return urlRepository.save(url);
    }

    @Transactional
    public UrlDto.Info count(String shortenedUrl) throws NotFoundException {
        Url url = urlRepository.findByShortenedUrl(shortenedUrl);

        if (isNull(url)) {
            throw new NotFoundException("It's not exist URL.");
        }
        url.plusCount();
        urlRepository.save(url);

        return new UrlDto.Info(url);
    }

    private boolean isNull(Object obj) {
        return obj == null;
    }
}
