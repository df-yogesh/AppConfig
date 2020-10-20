package com.dreamfolkstech.appconfig.service;

import com.dreamfolkstech.appconfig.service.dto.ProductServiceDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.dreamfolkstech.appconfig.domain.ProductService}.
 */
public interface ProductServiceService {

    /**
     * Save a productService.
     *
     * @param productServiceDTO the entity to save.
     * @return the persisted entity.
     */
    ProductServiceDTO save(ProductServiceDTO productServiceDTO);

    /**
     * Get all the productServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProductServiceDTO> findAll(Pageable pageable);


    /**
     * Get the "id" productService.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProductServiceDTO> findOne(Long id);

    /**
     * Delete the "id" productService.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
