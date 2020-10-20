package com.dreamfolkstech.appconfig.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dreamfolkstech.appconfig.service.dto.AppProductServiceDTO;
import com.dreamfolkstech.common.errors.ExternalBaseResponse;

/**
 * Service Interface for managing {@link com.dreamfolkstech.appconfig.domain.AppProductService}.
 */
public interface AppProductServiceService {

    /**
     * Save a appProductService.
     *
     * @param appProductServiceDTO the entity to save.
     * @return the persisted entity.
     */
    AppProductServiceDTO save(AppProductServiceDTO appProductServiceDTO);

    /**
     * Get all the appProductServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AppProductServiceDTO> findAll(Pageable pageable);


    /**
     * Get the "id" appProductService.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AppProductServiceDTO> findOne(Long id);

    /**
     * Delete the "id" appProductService.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

	ExternalBaseResponse findAllByProductCode(String code);
}
