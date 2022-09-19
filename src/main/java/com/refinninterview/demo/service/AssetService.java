package com.refinninterview.demo.service;

import com.refinninterview.demo.domain.Asset;
import com.refinninterview.demo.domain.enums.AssetTypeEnum;
import com.refinninterview.demo.dto.AssetDTO;
import com.refinninterview.demo.exception.AssetNotFoundException;
import com.refinninterview.demo.repository.AssetRepository;
import com.refinninterview.demo.web.mapper.AssetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssetService {

    private final AssetRepository assetRepository;
    private final AssetMapper assetMapper;

    public AssetDTO addAsset(AssetDTO assetDTO) {
        Asset mappedAsset = assetMapper.dtoToAsset(assetDTO);

        Instant now = Instant.now();
        mappedAsset.setCreatedAt(now);
        mappedAsset.setLastModifiedAt(now);

        Asset savedAsset = assetRepository.save(mappedAsset);
        assetDTO.setId(savedAsset.getId());
        return assetDTO;
    }

    public AssetDTO updateAsset(Long id, AssetDTO assetDTO) {
        Asset mappedAsset = assetMapper.dtoToAsset(assetDTO);
        mappedAsset.setId(id);
        mappedAsset.setLastModifiedAt(Instant.now());
        assetRepository.save(mappedAsset);

        return assetDTO;
    }

    public void deleteAssetById(Long assetId) {
        assetRepository.deleteById(assetId);
    }

    public AssetDTO findAssetById(Long assetId) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(AssetNotFoundException::new);

        return assetMapper.assetToDto(asset);
    }

    public Page<AssetDTO> findAssets(Integer page,
                                     Integer size,
                                     String assetType,
                                     BigDecimal minPrice,
                                     BigDecimal maxPrice) {

        List<Asset> assets = new ArrayList<>(assetRepository.findAll());

        if (assetType != null) {
            assets = assets.stream()
                    .filter((asset) -> asset.getAssetType() == Enum.valueOf(AssetTypeEnum.class, assetType))
                    .collect(Collectors.toList());
        }

        if (minPrice != null && maxPrice != null) {
            assets = assets.stream()
                    .filter(asset -> {
                                boolean isAssetPriceGreaterThanOrEqualsMin = asset.getPrice().compareTo(minPrice) >= 0;
                                boolean isAssetPriceLessThanOrEqualsMax = asset.getPrice().compareTo(maxPrice) <= 0;
                                return isAssetPriceGreaterThanOrEqualsMin && isAssetPriceLessThanOrEqualsMax;
                            }
                    )
                    .collect(Collectors.toList());

        } else if (minPrice != null) {
            assets = assets.stream()
                    .filter(asset -> asset.getPrice().compareTo(minPrice) >= 0)
                    .collect(Collectors.toList());

        } else if (maxPrice != null) {
            assets = assets.stream()
                    .filter(asset -> asset.getPrice().compareTo(maxPrice) <= 0)
                    .collect(Collectors.toList());
        }


        List<AssetDTO> mappedDTOs = assets.stream().map(assetMapper::assetToDto).collect(
                Collectors.toList());


        long totalAssets = mappedDTOs.size();

        Pageable pageable = PageRequest.of(page - 1, size);
        int start = (int) pageable.getOffset();
        int end = (int) Math.min((start + pageable.getPageSize()), totalAssets);

        return new PageImpl<>(mappedDTOs.subList(start, end), pageable, totalAssets);
    }
}
