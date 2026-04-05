package com.saurabh.finance_backend.dto;

import com.saurabh.finance_backend.entity.Type;
import jakarta.validation.constraints.*;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FinancialRecordDTO {

    @NotNull
    private Double amount;

    @NotNull
    private Type type;

    @NotBlank
    private String category;

    private LocalDate date;

    private String notes;
}