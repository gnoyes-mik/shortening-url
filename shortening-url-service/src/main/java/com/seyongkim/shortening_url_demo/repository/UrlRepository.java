package com.seyongkim.shortening_url_demo.repository;

import com.seyongkim.shortening_url_demo.model.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByOriginalUrl(String originalUrl);

    Url findByShortenedUrl(String shortenedUrl);

    @Query("SELECT coalesce(max(url.id), -1) FROM Url url")
    long findMaxId();
}
