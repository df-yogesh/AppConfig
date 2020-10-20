package com.dreamfolkstech.appconfig.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.dreamfolkstech.appconfig.domain.Partner;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
import com.dreamfolkstech.common.repository.BaseRepository;

/**
 * Spring Data  repository for the Partner entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PartnerRepository extends BaseRepository<Partner, Long> {

	Page<Partner> findAllByStatus(GenericStatus enabled, Pageable pageable);
}
