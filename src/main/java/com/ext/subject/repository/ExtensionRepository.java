package com.ext.subject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ext.subject.domain.Extension;

@Repository
public interface ExtensionRepository extends JpaRepository<Extension, Long> {

}
