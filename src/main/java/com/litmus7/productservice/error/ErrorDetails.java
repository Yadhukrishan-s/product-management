package com.litmus7.productservice.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorDetails {

    private LocalDate date;
    private String message;
    private HttpStatus status;
    private String details;
}
