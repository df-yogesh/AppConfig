package com.dreamfolkstech.appconfig.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dreamfolkstech.appconfig.service.MasterUserFieldService;
import com.dreamfolkstech.appconfig.service.dto.MasterUserFieldDTO;
import com.dreamfolkstech.appconfig.web.rest.errors.ErrorConstants;
import com.dreamfolkstech.common.dto.BaseResponse;
import com.dreamfolkstech.common.errors.CustomException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.dreamfolkstech.appconfig.domain.MasterUserField}.
 */
@RestController
@RequestMapping("/api")
public class MasterUserFieldResource {

    private final Logger log = LoggerFactory.getLogger(MasterUserFieldResource.class);

    private static final String ENTITY_NAME = "MasterUserField";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MasterUserFieldService masterUserFieldService;

    public MasterUserFieldResource(MasterUserFieldService masterUserFieldService) {
        this.masterUserFieldService = masterUserFieldService;
    }

    /**
     * {@code POST  /master-user-fields} : Create a new masterUserField.
     *
     * @param masterUserFieldDTO the masterUserFieldDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new masterUserFieldDTO, or with status {@code 400 (Bad Request)} if the masterUserField has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/master-user-fields")
    public ResponseEntity<BaseResponse> createMasterUserField(@Valid @RequestBody MasterUserFieldDTO masterUserFieldDTO) throws URISyntaxException {
        log.debug("REST request to save MasterUserField : {}", masterUserFieldDTO);
        if (masterUserFieldDTO.getId() != null) {
            throw new CustomException(ErrorConstants.ENTITY_CREATE_ID_ERROR, ENTITY_NAME);
        }
        MasterUserFieldDTO result = masterUserFieldService.save(masterUserFieldDTO);
        return ResponseEntity.created(new URI("/api/master-user-fields/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(new BaseResponse(result));
    }

    /**
     * {@code PUT  /master-user-fields} : Updates an existing masterUserField.
     *
     * @param masterUserFieldDTO the masterUserFieldDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated masterUserFieldDTO,
     * or with status {@code 400 (Bad Request)} if the masterUserFieldDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the masterUserFieldDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/master-user-fields")
    public ResponseEntity<BaseResponse> updateMasterUserField(@Valid @RequestBody MasterUserFieldDTO masterUserFieldDTO) throws URISyntaxException {
        log.debug("REST request to update MasterUserField : {}", masterUserFieldDTO);
        if (masterUserFieldDTO.getId() == null) {
            throw new CustomException(ErrorConstants.ENTITY_FETCH_MISSING_ID_ERROR, ENTITY_NAME);
        }
        MasterUserFieldDTO result = masterUserFieldService.save(masterUserFieldDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, masterUserFieldDTO.getId().toString()))
            .body(new BaseResponse(result));
    }

    /**
     * {@code GET  /master-user-fields} : get all the masterUserFields.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of masterUserFields in body.
     */
    @GetMapping("/master-user-fields")
    public ResponseEntity<BaseResponse> getAllMasterUserFields(Pageable pageable) {
        log.debug("REST request to get a page of MasterUserFields");
        Page<MasterUserFieldDTO> page = masterUserFieldService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(new BaseResponse( page.getContent()));
    }

    /**
     * {@code GET  /master-user-fields/:id} : get the "id" masterUserField.
     *
     * @param id the id of the masterUserFieldDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the masterUserFieldDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/master-user-fields/{id}")
    public ResponseEntity<BaseResponse> getMasterUserField(@PathVariable Long id) {
        log.debug("REST request to get MasterUserField : {}", id);
        Optional<MasterUserFieldDTO> masterUserFieldDTO = masterUserFieldService.findOne(id);
        return ResponseUtil.wrapOrNotFound(masterUserFieldDTO.map(BaseResponse::new));
    }

    /**
     * {@code DELETE  /master-user-fields/:id} : delete the "id" masterUserField.
     *
     * @param id the id of the masterUserFieldDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/master-user-fields/{id}")
    public ResponseEntity<Void> deleteMasterUserField(@PathVariable Long id) {
        log.debug("REST request to delete MasterUserField : {}", id);
        masterUserFieldService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
