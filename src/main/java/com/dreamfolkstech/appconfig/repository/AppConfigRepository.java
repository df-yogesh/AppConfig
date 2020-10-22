package com.dreamfolkstech.appconfig.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dreamfolkstech.appconfig.domain.AppConfig;
import com.dreamfolkstech.appconfig.service.util.ModelConstants;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
import com.dreamfolkstech.common.repository.BaseRepository;

/**
 * Spring Data repository for the AppConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppConfigRepository extends BaseRepository<AppConfig, Long> {

	Page<AppConfig> findAllByStatus(GenericStatus enabled, Pageable pageable);

	@Query("Select ac from AppConfig ac where ac.code=:appCode and"
			+ " (ac.deviceType=:deviceType or ac.deviceType is null) and status = "
			+ ModelConstants.GENERICSTATUS_ENABLED)
	List<AppConfig> fetchAllByCode(@Param("appCode") String appCode, @Param("deviceType") String deviceType);

	
	@Query("Select ac from AppConfig ac where ac.code=:appCode and"
			+ " (ac.deviceType=:deviceType or ac.deviceType is null) and ac.createdDate> :instant or ac.lastModifiedDate> :instant and status = "
			+ ModelConstants.GENERICSTATUS_ENABLED)
	List<AppConfig> fetchAllByCodeAndTime(@Param("appCode") String appCode,  @Param("deviceType") String deviceType, @Param("instant") Instant instant);
}
