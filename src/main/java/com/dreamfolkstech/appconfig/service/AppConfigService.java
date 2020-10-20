package com.dreamfolkstech.appconfig.service;

import com.dreamfolkstech.appconfig.service.dto.AppConfigDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.dreamfolkstech.appconfig.domain.AppConfig}.
 */
public interface AppConfigService {

    /**
     * Save a appConfig.
     *
     * @param appConfigDTO the entity to save.
     * @return the persisted entity.
     */
    AppConfigDTO save(AppConfigDTO appConfigDTO);

    /**
     * Get all the appConfigs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AppConfigDTO> findAll(Pageable pageable);


    /**
     * Get the "id" appConfig.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AppConfigDTO> findOne(Long id);

    /**
     * Delete the "id" appConfig.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
