package com.refinninterview.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ExceptionDTO {
    private int status;
    private Instant timestamp;
    private String path;
    private String error;
}
