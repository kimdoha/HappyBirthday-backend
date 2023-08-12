package com.rocket.birthday.repository.report;

import com.rocket.birthday.model.report.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository
    extends JpaRepository<ReportEntity, Long> {

}
