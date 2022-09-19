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
public class PaginateContactDTO {
    private int page;
    private int size;
    private int total;
    private int totalPage;
    private List<ContactDTO> contacts;
}
