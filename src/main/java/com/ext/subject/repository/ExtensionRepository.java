package com.ext.subject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ext.subject.domain.Extension;
import com.ext.subject.util.common.ExtensionCategory;

@Repository
public interface ExtensionRepository extends JpaRepository<Extension, Long> {

	List<Extension> findByCategory(ExtensionCategory category);

	Optional<Extension> findByName(String name);

}
