package com.dreamfolkstech.appconfig.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.dreamfolkstech.appconfig.domain.MasterUserField;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
import com.dreamfolkstech.common.repository.BaseRepository;

/**
 * Spring Data  repository for the MasterUserField entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MasterUserFieldRepository extends BaseRepository<MasterUserField, Long> {

	Page<MasterUserField> findAllByStatus(GenericStatus enabled, Pageable pageable);
}
