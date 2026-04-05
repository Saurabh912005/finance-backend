package com.saurabh.finance_backend.specification;

import com.saurabh.finance_backend.entity.FinancialRecord;
import com.saurabh.finance_backend.entity.Type;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FinancialSpecification {

    public static Specification<FinancialRecord> filter(
            String category,
            Type type,
            LocalDate startDate,
            LocalDate endDate
    ) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            // Filter by category
            if (category != null && !category.isEmpty()) {
                predicates.add(cb.equal(root.get("category"), category));
            }

            // Filter by type (INCOME / EXPENSE)
            if (type != null) {
                predicates.add(cb.equal(root.get("type"), type));
            }

            // Filter by date range
            if (startDate != null && endDate != null) {
                predicates.add(cb.between(root.get("date"), startDate, endDate));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}