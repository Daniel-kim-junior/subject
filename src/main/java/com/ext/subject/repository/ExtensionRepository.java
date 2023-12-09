package com.ext.subject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ext.subject.domain.Extension;
import com.ext.subject.dto.ExtensionDto.GetCustomResDto;
import com.ext.subject.dto.ExtensionDto.GetFixedResDto;

@Repository
public interface ExtensionRepository extends JpaRepository<Extension, Long> {

	@Query(value = "select new GetFixedResDto(e.name, e.isActivate) from Extension e where e.category = :category", nativeQuery = true)
	List<GetFixedResDto> findByCategoryFixed(@Param("category") String category);

	Extension findByName(String name);

	@Query(value = "select new GetCustomResDto(e.name) from Extension e where e.category = :category", nativeQuery = true)
	List<GetCustomResDto> findByCategoryCustom(@Param("category") String category);
}
