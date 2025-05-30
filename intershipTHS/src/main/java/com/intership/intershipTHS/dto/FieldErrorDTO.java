package com.intership.intershipTHS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldErrorDTO {
    private String field;
    private String message;
}
