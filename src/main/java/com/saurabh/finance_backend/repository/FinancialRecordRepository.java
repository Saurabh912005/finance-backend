package com.saurabh.finance_backend.repository;

import com.saurabh.finance_backend.entity.FinancialRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialRecordRepository extends
        JpaRepository<FinancialRecord, Long>,
        JpaSpecificationExecutor<FinancialRecord> {
}