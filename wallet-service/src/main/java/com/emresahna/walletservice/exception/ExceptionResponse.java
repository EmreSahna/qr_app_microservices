package com.emresahna.walletservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    private Integer status;
    private String code;
    private String path;
    private OffsetDateTime timestamp;
    private String detail;
}
