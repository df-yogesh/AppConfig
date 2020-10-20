package com.dreamfolkstech.appconfig.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.dreamfolkstech.appconfig.domain.ProductOffering;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
import com.dreamfolkstech.common.repository.BaseRepository;

/**
 * Spring Data  repository for the ProductOffering entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductOfferingRepository extends BaseRepository<ProductOffering, Long> {

	Page<ProductOffering> findAllByStatus(GenericStatus enabled, Pageable pageable);
}
