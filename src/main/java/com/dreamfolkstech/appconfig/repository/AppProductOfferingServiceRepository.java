package com.dreamfolkstech.appconfig.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.dreamfolkstech.appconfig.domain.AppProductOfferingService;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
import com.dreamfolkstech.common.repository.BaseRepository;

/**
 * Spring Data repository for the AppProductService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppProductOfferingServiceRepository extends BaseRepository<AppProductOfferingService, Long> {

	Page<AppProductOfferingService> findAllByStatus(GenericStatus enabled, Pageable pageable);
}
