package com.dreamfolkstech.appconfig.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dreamfolkstech.appconfig.domain.AppUserFieldMap;
import com.dreamfolkstech.appconfig.service.util.ModelConstants;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
import com.dreamfolkstech.common.repository.BaseRepository;

/**
 * Spring Data repository for the AppUserFieldMap entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppUserFieldMapRepository extends BaseRepository<AppUserFieldMap, Long> {

	Page<AppUserFieldMap> findAllByStatus(GenericStatus enabled, Pageable pageable);

	@Query("select aufm from AppUserFieldMap aufm, Application a where aufm.appId = a.id and a.code=:code"
			+ " and aufm.status=" + ModelConstants.GENERICSTATUS_ENABLED)
	List<AppUserFieldMap> findAllByCode(@Param("code") String code);
}
