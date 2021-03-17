package com.seyongkim.shortening_url_demo.service;

import com.seyongkim.shortening_url_demo.model.dto.UrlDto;
import com.seyongkim.shortening_url_demo.model.entity.Url;
import com.seyongkim.shortening_url_demo.repository.UrlRepository;
import com.seyongkim.shortening_url_demo.utils.Base52;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShorteningURLService {

    private final UrlRepository urlRepository;

    private final String apiURL = "http://localhost:8888/";

    /**
     * 기존 URL을 기반으로 단축 URL을 생성하는 메소드
     * 만약 기존 URL이 이미 존재한다면, 등록된 단축 Url을 리턴한다
     * @param originalUrl originalUrl을 담은 Dto 객체
     * @return 기존 URL과 단축 URL이 담긴 UrlDto.Response 객체
     */
    @Transactional
    public UrlDto.Response create(String originalUrl) {
        Url url = urlRepository.findByOriginalUrl(originalUrl);

        String shortenedUrl;
        if (!isNull(url)) {
            shortenedUrl = url.getShortenedUrl();

            return new UrlDto.Response(shortenedUrl);
        }

        shortenedUrl = saveNewUrl(originalUrl).getShortenedUrl();

        return new UrlDto.Response(shortenedUrl);
    }
    /**
     * 기존 URL을 Base52로 인코딩하여 단축 URL로 변환한 뒤, repository에 저장하는 메소드
     * @param originalUrl 기존 URL
     * @return Url entity
     */
    private Url saveNewUrl(String originalUrl) {
        long keyNumber = urlRepository.findMaxId() + 1L;

        Url url = Url.builder()
                .originalUrl(originalUrl)
                .shortenedUrl(apiURL + Base52.encode(keyNumber))
                .build();

        return urlRepository.save(url);
    }

    /**
     * 단축 URL이 호출 되었을 때 실행이 되는 메소드
     * 해당 단축 URL의 카운트 값을 1 증가 시킨다
     * @param shortenedUrl 단축 URL
     * @return Url entity 전체 필드 값을 담은 UrlDto.Info 객체
     * @throws NotFoundException
     */
    @Transactional
    public UrlDto.Info count(String shortenedUrl) throws NotFoundException {
        Url url = urlRepository.findByShortenedUrl(apiURL + shortenedUrl);

        throwNotFoundExceptionIfIsNull(url);

        url.plusCount();
        urlRepository.save(url);

        return new UrlDto.Info(url);
    }

    private boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * 단축 URL 기준으로 요청 수를 조회하는 메소드
     * @param shortenedUrl 단축 URL
     * @return Url entity 전체 필드 값을 담은 UrlDto.Info 객체
     * @throws NotFoundException
     */
    public UrlDto.Info getNumberOfUrlRequestByShortenedUrl(String shortenedUrl) throws NotFoundException {
        Url url = urlRepository.findByShortenedUrl(shortenedUrl);

        throwNotFoundExceptionIfIsNull(url);

        return new UrlDto.Info(url);
    }

    /**
     * 기존 URL 기준으로 요청 수를 조회하는 메소드
     * @param originalUrl 기존 URL
     * @return Url entity 전체 필드 값을 담은 UrlDto.Info 객체
     * @throws NotFoundException
     */
    public UrlDto.Info getNumberOfUrlRequestByOriginalUrl(String originalUrl) throws NotFoundException {
        Url url = urlRepository.findByOriginalUrl(originalUrl);

        throwNotFoundExceptionIfIsNull(url);

        return new UrlDto.Info(url);
    }

    /**
     * repository에서 entity를 조회한 뒤 null인지 아닌지 검증할 때 호출되는 메소드
     * @param url
     * @throws NotFoundException
     */
    private void throwNotFoundExceptionIfIsNull(Url url) throws NotFoundException {
        if (isNull(url)) {
            throw new NotFoundException("This URL is not found");
        }
    }
}
