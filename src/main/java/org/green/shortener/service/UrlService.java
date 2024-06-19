package org.green.shortener.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.green.shortener.domain.ShortenedUrlRequest;
import org.green.shortener.domain.entity.UrlInfo;
import org.green.shortener.mapper.UrlMapper;
import org.green.shortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlService {
    private final UrlRepository urlRepository;
    private final UrlMapper urlMapper;


    @Transactional
    public UrlInfo shortUrl(ShortenedUrlRequest shortenedUrlRequest) {
        var randomGenerated = RandomStringUtils.randomAlphabetic(6);
        log.info("ID generated: {}", randomGenerated);
        UrlInfo urlInfo = urlMapper.toUrlInfo(shortenedUrlRequest);
        urlInfo.setId(randomGenerated);
        return urlRepository.save(urlInfo);

    }

    public UrlInfo redirectShortened(String id) {
        return urlRepository.findById(id).orElseThrow();
    }
}
