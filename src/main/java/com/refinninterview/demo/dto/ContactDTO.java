package com.refinninterview.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {
    private Long id;
    private String name;
    private String telNo;
    private String lineId;
    private String status;
    private AssetDTO asset;
    private Instant callBackTime;
    private Instant createdAt;
    private Instant lastModifiedAt;

}
