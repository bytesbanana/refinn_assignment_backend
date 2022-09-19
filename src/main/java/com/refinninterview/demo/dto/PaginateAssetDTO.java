package com.refinninterview.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginateAssetDTO {
    private Integer page;
    private Integer size;
    private Integer total;
    private Integer totalPage;
    private List<AssetDTO> assets;
}
