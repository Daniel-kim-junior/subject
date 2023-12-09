package com.ext.subject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ext.subject.domain.ExtensionLog;
@Repository
public interface ExtensionLogRepository extends JpaRepository<ExtensionLog, Long> {
}
