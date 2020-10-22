package com.dreamfolkstech.appconfig.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dreamfolkstech.appconfig.domain.AppUserFieldMap;
import com.dreamfolkstech.appconfig.service.dto.AppUserFieldMapDTO;
import com.dreamfolkstech.common.errors.ExternalBaseResponse;

/**
 * Service Interface for managing {@link com.dreamfolkstech.appconfig.domain.AppUserFieldMap}.
 */
public interface AppUserFieldMapService {

    /**
     * Save a appUserFieldMap.
     *
     * @param appUserFieldMapDTO the entity to save.
     * @return the persisted entity.
     */
    AppUserFieldMapDTO save(AppUserFieldMapDTO appUserFieldMapDTO);

    /**
     * Get all the appUserFieldMaps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AppUserFieldMapDTO> findAll(Pageable pageable);


    /**
     * Get the "id" appUserFieldMap.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AppUserFieldMapDTO> findOne(Long id);

    /**
     * Delete the "id" appUserFieldMap.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

	/**find user registration fields by app code
	 * @param code
	 * @return
	 */
	ExternalBaseResponse findAllByCode(String code);
}
