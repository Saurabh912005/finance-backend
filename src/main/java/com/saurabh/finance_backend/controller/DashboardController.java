package com.saurabh.finance_backend.controller;

import com.saurabh.finance_backend.service.DashboardService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/analytics/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService service;

    @GetMapping("/summary")
    public Map<String, Double> getSummary() {
        return service.getSummary();
    }
}