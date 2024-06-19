package org.green.shortener.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "url_info")
public class UrlInfo {
    @Id
    @Column( nullable = false, unique = true)
    private String id;
    @Column(nullable = false)
    private String url;
    @Column
    private String title;
}
