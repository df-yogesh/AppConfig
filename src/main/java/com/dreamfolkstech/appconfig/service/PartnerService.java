package com.dreamfolkstech.appconfig.service;

import com.dreamfolkstech.appconfig.service.dto.PartnerDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.dreamfolkstech.appconfig.domain.Partner}.
 */
public interface PartnerService {

    /**
     * Save a partner.
     *
     * @param partnerDTO the entity to save.
     * @return the persisted entity.
     */
    PartnerDTO save(PartnerDTO partnerDTO);

    /**
     * Get all the partners.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PartnerDTO> findAll(Pageable pageable);


    /**
     * Get the "id" partner.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PartnerDTO> findOne(Long id);

    /**
     * Delete the "id" partner.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
