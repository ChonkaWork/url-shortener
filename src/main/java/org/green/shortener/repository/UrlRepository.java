package org.green.shortener.repository;

import org.green.shortener.domain.entity.UrlInfo;
import org.springframework.data.repository.CrudRepository;

public interface UrlRepository extends CrudRepository<UrlInfo, String> {
}
