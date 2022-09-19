package com.refinninterview.demo.web.controller;

import com.refinninterview.demo.dto.PaginateAssetDTO;
import com.refinninterview.demo.service.AssetService;
import com.refinninterview.demo.dto.AssetDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/assets")
@RequiredArgsConstructor
@Slf4j
public class AssetController {

    private final AssetService assetService;

    @PostMapping
    public ResponseEntity<AssetDTO> createAsset(@Valid @RequestBody AssetDTO assetDTO) {
        AssetDTO result = assetService.addAsset(assetDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetDTO> updateAsset(@PathVariable Long id, @Valid @RequestBody AssetDTO assetDTO) {
        AssetDTO result = assetService.updateAsset(id, assetDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<AssetDTO> deleteAsset(@PathVariable Long id) {
        assetService.deleteAssetById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetDTO> getAssetById(@PathVariable Long id) {
        AssetDTO result = assetService.findAssetById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<PaginateAssetDTO> getAssets(@Param("page") Integer page,
                                                      @Param("size") Integer size,
                                                      @Param("assetType") String assetType,
                                                      @Param("min") BigDecimal min,
                                                      @Param("max") BigDecimal max) {
        if (page == null) {
            page = 1;
        }
        if (size == null) {
            size = 10;
        }

        Page<AssetDTO> pageAssetDTO = assetService.findAssets(page, size, assetType, min, max);


        PaginateAssetDTO result = PaginateAssetDTO.builder()
                .page(pageAssetDTO.getNumber() + 1)
                .size(pageAssetDTO.getSize())
                .total((int) pageAssetDTO.getTotalElements())
                .totalPage(pageAssetDTO.getTotalPages())
                .assets(pageAssetDTO.getContent())
                .build();

        return ResponseEntity.ok(result);

    }
}
