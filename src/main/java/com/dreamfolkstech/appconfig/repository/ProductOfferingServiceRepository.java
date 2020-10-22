package com.dreamfolkstech.appconfig.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dreamfolkstech.appconfig.domain.ProductOfferingService;
import com.dreamfolkstech.appconfig.service.util.ModelConstants;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
import com.dreamfolkstech.common.repository.BaseRepository;

/**
 * Spring Data repository for the ProductService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductOfferingServiceRepository extends BaseRepository<ProductOfferingService, Long> {

	Page<ProductOfferingService> findAllByStatus(GenericStatus enabled, Pageable pageable);

	@Query("SELECT pos FROM ProductOfferingService pos, AppProductOfferingService apos where"
			+ " pos.id = apos.productOfferingServiceId " + " and apos.status = " + ModelConstants.GENERICSTATUS_ENABLED
			+ " and pos.status = " + ModelConstants.GENERICSTATUS_ENABLED)
	List<ProductOfferingService> findAllByStatus(GenericStatus enabled);

}
