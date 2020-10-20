package com.dreamfolkstech.appconfig.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.dreamfolkstech.appconfig.domain.AppUserFieldMap;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
import com.dreamfolkstech.common.repository.BaseRepository;

/**
 * Spring Data  repository for the AppUserFieldMap entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppUserFieldMapRepository extends BaseRepository<AppUserFieldMap, Long> {

	Page<AppUserFieldMap> findAllByStatus(GenericStatus enabled, Pageable pageable);
}
