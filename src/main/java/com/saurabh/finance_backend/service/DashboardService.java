package com.saurabh.finance_backend.service;

import com.saurabh.finance_backend.entity.FinancialRecord;
import com.saurabh.finance_backend.entity.Type;
import com.saurabh.finance_backend.repository.FinancialRecordRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final FinancialRecordRepository repo;

    public Map<String, Double> getSummary() {

        List<FinancialRecord> records = repo.findAll();

        double income = records.stream()
                .filter(r -> r.getType() == Type.INCOME)
                .mapToDouble(FinancialRecord::getAmount)
                .sum();

        double expense = records.stream()
                .filter(r -> r.getType() == Type.EXPENSE)
                .mapToDouble(FinancialRecord::getAmount)
                .sum();

        Map<String, Double> result = new HashMap<>();
        result.put("income", income);
        result.put("expense", expense);
        result.put("balance", income - expense);

        return result;
    }
}