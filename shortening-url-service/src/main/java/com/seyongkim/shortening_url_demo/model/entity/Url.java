package com.seyongkim.shortening_url_demo.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalUrl;

    private String shortenedUrl;

    private Long count;

    @Builder
    public Url(String originalUrl, String shortenedUrl) {
        this.originalUrl = originalUrl;
        this.shortenedUrl = shortenedUrl;
    }

    @PrePersist
    private void prePersist() {
        this.count = this.count == null ? 0 : this.count;
    }

    public void plusCount() {
        this.count += 1L;
    }
}
