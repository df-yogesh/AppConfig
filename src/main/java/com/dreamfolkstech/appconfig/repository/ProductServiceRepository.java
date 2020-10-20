package com.dreamfolkstech.appconfig.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.dreamfolkstech.appconfig.domain.ProductService;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
import com.dreamfolkstech.common.repository.BaseRepository;

/**
 * Spring Data  repository for the ProductService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductServiceRepository extends BaseRepository<ProductService, Long> {

	Page<ProductService> findAllByStatus(GenericStatus enabled, Pageable pageable);
}
