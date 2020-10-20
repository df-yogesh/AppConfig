package com.dreamfolkstech.appconfig.service;

import com.dreamfolkstech.appconfig.service.dto.MasterUserFieldDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.dreamfolkstech.appconfig.domain.MasterUserField}.
 */
public interface MasterUserFieldService {

    /**
     * Save a masterUserField.
     *
     * @param masterUserFieldDTO the entity to save.
     * @return the persisted entity.
     */
    MasterUserFieldDTO save(MasterUserFieldDTO masterUserFieldDTO);

    /**
     * Get all the masterUserFields.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MasterUserFieldDTO> findAll(Pageable pageable);


    /**
     * Get the "id" masterUserField.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MasterUserFieldDTO> findOne(Long id);

    /**
     * Delete the "id" masterUserField.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
