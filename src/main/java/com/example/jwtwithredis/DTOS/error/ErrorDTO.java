package com.example.jwtwithredis.DTOS.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDTO {
    private String message;
    private int status;
}
