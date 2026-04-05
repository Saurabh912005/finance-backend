package com.saurabh.finance_backend.controller;

import com.saurabh.finance_backend.entity.FinancialRecord;
import com.saurabh.finance_backend.entity.Type;
import com.saurabh.finance_backend.service.FinancialService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/admin/records")
@RequiredArgsConstructor
public class FinancialController {

    private final FinancialService service;

    @PostMapping
    public FinancialRecord create(@RequestBody FinancialRecord record) {
        return service.create(record);
    }

    @GetMapping
    public Page<FinancialRecord> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        return service.getAll(page, size, sortBy, direction);
    }

    @GetMapping("/filter")
    public Page<FinancialRecord> filter(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Type type,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return service.filter(category, type, startDate, endDate, page, size);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted successfully";
    }
}