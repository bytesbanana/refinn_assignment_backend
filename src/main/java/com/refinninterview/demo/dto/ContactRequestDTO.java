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
public class ContactRequestDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String telNo;
    private String lineId;
    private String status;
    @NotNull
    private Long assetId;
    private Instant callBackTime;

}
