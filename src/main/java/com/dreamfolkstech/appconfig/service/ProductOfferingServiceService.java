package com.dreamfolkstech.appconfig.service;

import com.dreamfolkstech.appconfig.service.dto.ProductOfferingServiceDTO;
import com.dreamfolkstech.common.errors.ExternalBaseResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.dreamfolkstech.appconfig.domain.ProductOfferingService}.
 */
public interface ProductOfferingServiceService {

    /**
     * Save a productService.
     *
     * @param productOfferingServiceDTO the entity to save.
     * @return the persisted entity.
     */
    ProductOfferingServiceDTO save(ProductOfferingServiceDTO productOfferingServiceDTO);

    /**
     * Get all the productServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProductOfferingServiceDTO> findAll(Pageable pageable);


    /**
     * Get the "id" productService.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProductOfferingServiceDTO> findOne(Long id);

    /**
     * Delete the "id" productService.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
	
}
