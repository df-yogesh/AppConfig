package com.dreamfolkstech.appconfig.service;

import com.dreamfolkstech.appconfig.service.dto.ProductOfferingDTO;
import com.dreamfolkstech.appconfig.service.util.UtilityFunctions;
import com.dreamfolkstech.appconfig.web.rest.errors.ErrorConstants;
import com.dreamfolkstech.common.errors.ExternalBaseResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.dreamfolkstech.appconfig.domain.ProductOffering}.
 */
public interface ProductOfferingService {

    /**
     * Save a productOffering.
     *
     * @param productOfferingDTO the entity to save.
     * @return the persisted entity.
     */
    ProductOfferingDTO save(ProductOfferingDTO productOfferingDTO);

    /**
     * Get all the productOfferings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProductOfferingDTO> findAll(Pageable pageable);


    /**
     * Get the "id" productOffering.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProductOfferingDTO> findOne(Long id);

    /**
     * Delete the "id" productOffering.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
    
    /**
	 * @param code
	 * @return
	 */
	ExternalBaseResponse findAllByAppCode(String code);
}
