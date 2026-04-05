package com.saurabh.finance_backend.service;

import com.saurabh.finance_backend.entity.*;
import com.saurabh.finance_backend.repository.FinancialRecordRepository;
import com.saurabh.finance_backend.specification.FinancialSpecification;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class FinancialService {

    private final FinancialRecordRepository repo;

    public FinancialRecord create(FinancialRecord record) {
        return repo.save(record);
    }

    public Page<FinancialRecord> getAll(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return repo.findAll(pageable);
    }

    public Page<FinancialRecord> filter(
            String category,
            Type type,
            LocalDate startDate,
            LocalDate endDate,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        Specification<FinancialRecord> spec =
                FinancialSpecification.filter(category, type, startDate, endDate);

        return repo.findAll(spec, pageable);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}