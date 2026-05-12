package com.duoc.splim.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorApi {

    private int status;
    private String mensaje;
    private String error;
}
