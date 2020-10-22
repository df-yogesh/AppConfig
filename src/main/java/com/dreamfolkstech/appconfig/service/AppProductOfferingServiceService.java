package com.dreamfolkstech.appconfig.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dreamfolkstech.appconfig.service.dto.AppProductOfferingServiceDTO;
import com.dreamfolkstech.common.errors.ExternalBaseResponse;

/**
 * Service Interface for managing {@link com.dreamfolkstech.appconfig.domain.AppProductOfferingService}.
 */
public interface AppProductOfferingServiceService {

    /**
     * Save a appProductService.
     *
     * @param appProductOfferingServiceDTO the entity to save.
     * @return the persisted entity.
     */
    AppProductOfferingServiceDTO save(AppProductOfferingServiceDTO appProductOfferingServiceDTO);

    /**
     * Get all the appProductServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AppProductOfferingServiceDTO> findAll(Pageable pageable);


    /**
     * Get the "id" appProductService.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AppProductOfferingServiceDTO> findOne(Long id);

    /**
     * Delete the "id" appProductService.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

}