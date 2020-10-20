package com.dreamfolkstech.appconfig.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dreamfolkstech.appconfig.domain.AppProductService;
import com.dreamfolkstech.appconfig.service.util.ModelConstants;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
import com.dreamfolkstech.common.repository.BaseRepository;

/**
 * Spring Data repository for the AppProductService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppProductServiceRepository extends BaseRepository<AppProductService, Long> {

	Page<AppProductService> findAllByStatus(GenericStatus enabled, Pageable pageable);

	@Query("Select aps from AppProductService aps, Application a where aps.application = a.id and a.code =:code and aps.status=("
			+ ModelConstants.GENERICSTATUS_ENABLED + ")")
	List<AppProductService> findAllByProductCode(@Param("code") String code);
}
