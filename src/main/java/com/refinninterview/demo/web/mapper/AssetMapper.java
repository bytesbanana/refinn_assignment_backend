package com.refinninterview.demo.web.mapper;

import com.refinninterview.demo.domain.Asset;
import com.refinninterview.demo.dto.AssetDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public abstract class AssetMapper {

    public abstract AssetDTO assetToDto(Asset asset);

    public abstract Asset dtoToAsset(AssetDTO assetDTO);
}
