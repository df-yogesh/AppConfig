package com.dreamfolkstech.appconfig.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dreamfolkstech.appconfig.domain.ProductOffering;
import com.dreamfolkstech.appconfig.domain.ProductOfferingService;
import com.dreamfolkstech.appconfig.service.util.ModelConstants;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
import com.dreamfolkstech.common.repository.BaseRepository;

/**
 * Spring Data repository for the ProductOffering entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductOfferingRepository extends BaseRepository<ProductOffering, Long> {

	Page<ProductOffering> findAllByStatus(GenericStatus enabled, Pageable pageable);
	
	@Query("SELECT DISTINCT pos FROM ProductOffering po, ProductOfferingService pos, Application a, AppProductOfferingService apos where"
			+ " pos.id = apos.productOfferingServiceId and apos.application = a.id and po.id=pos.productOffering and a.code=:code "
			+ " and apos.status = " + ModelConstants.GENERICSTATUS_ENABLED + " and pos.status = "
			+ ModelConstants.GENERICSTATUS_ENABLED + " and a.status = " + ModelConstants.GENERICSTATUS_ENABLED
			+ " and po.status = " + ModelConstants.GENERICSTATUS_ENABLED)
	List<ProductOfferingService> findAllByProductCode(@Param("code") String code);
}
