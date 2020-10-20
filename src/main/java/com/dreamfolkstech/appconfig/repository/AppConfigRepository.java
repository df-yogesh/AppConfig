package com.dreamfolkstech.appconfig.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.dreamfolkstech.appconfig.domain.AppConfig;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
import com.dreamfolkstech.common.repository.BaseRepository;

/**
 * Spring Data  repository for the AppConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppConfigRepository extends BaseRepository<AppConfig, Long> {

	Page<AppConfig> findAllByStatus(GenericStatus enabled, Pageable pageable);
}
