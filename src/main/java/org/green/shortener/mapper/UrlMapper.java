package org.green.shortener.mapper;

import org.green.shortener.domain.ShortenedUrlRequest;
import org.green.shortener.domain.entity.UrlInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", implementationPackage = "org.green.shortener.mapper")
public interface UrlMapper {

    UrlInfo toUrlInfo(ShortenedUrlRequest shortenedUrlRequest);
}