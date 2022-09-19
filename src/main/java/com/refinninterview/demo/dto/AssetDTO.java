package com.refinninterview.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetDTO {
    private Long id;
    @NotBlank
    private String title;
    private String description;
    private String imgUrl;
    @NotBlank
    private String assetType;
    @NotNull
    private Double price;
    private Double latitude;
    private Double longitude;
    private Instant createdAt;
    private Instant lastModifiedAt;
}
