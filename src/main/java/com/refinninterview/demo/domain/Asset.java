package com.refinninterview.demo.domain;

import com.refinninterview.demo.domain.enums.AssetTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.Instant;

@Entity(name = "assets")
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Title is mandatory")
    private String title;

    @Column
    private String description;

    @Column
    private String imgUrl;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Column
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "asset_type")
    private AssetTypeEnum assetType;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "last_modified_at")
    private Instant lastModifiedAt;

}
