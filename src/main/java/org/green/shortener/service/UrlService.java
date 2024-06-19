package org.green.shortener.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.green.shortener.domain.entity.UrlInfo;
import org.green.shortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlService {
    private final UrlRepository urlRepository;


    @Transactional
    public UrlInfo shortUrl(String url, String title) {
        String randomGenerated = RandomStringUtils.randomAlphabetic(6);
        log.info("ID generated: {}", randomGenerated);
        UrlInfo urlInfo = new UrlInfo();
        urlInfo.setId(randomGenerated);
        urlInfo.setUrl(url);
        urlInfo.setTitle(title);
        return urlRepository.save(urlInfo);

    }

    public UrlInfo redirectShortened(String id) {
        return urlRepository.findById(id).orElseThrow();
    }
}
