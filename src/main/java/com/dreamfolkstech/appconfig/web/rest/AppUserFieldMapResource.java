package com.dreamfolkstech.appconfig.web.rest;

import com.dreamfolkstech.appconfig.service.AppUserFieldMapService;
import com.dreamfolkstech.appconfig.web.rest.errors.BadRequestAlertException;
import com.dreamfolkstech.appconfig.web.rest.errors.ErrorConstants;
import com.dreamfolkstech.common.dto.BaseResponse;
import com.dreamfolkstech.common.errors.CustomException;
import com.dreamfolkstech.appconfig.service.dto.AppUserFieldMapDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.dreamfolkstech.appconfig.domain.AppUserFieldMap}.
 */
@RestController
@RequestMapping("/api")
public class AppUserFieldMapResource {

    private final Logger log = LoggerFactory.getLogger(AppUserFieldMapResource.class);

    private static final String ENTITY_NAME = "AppUserFieldMap";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AppUserFieldMapService appUserFieldMapService;

    public AppUserFieldMapResource(AppUserFieldMapService appUserFieldMapService) {
        this.appUserFieldMapService = appUserFieldMapService;
    }

    /**
     * {@code POST  /app-user-field-maps} : Create a new appUserFieldMap.
     *
     * @param appUserFieldMapDTO the appUserFieldMapDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new appUserFieldMapDTO, or with status {@code 400 (Bad Request)} if the appUserFieldMap has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/app-user-field-maps")
    public ResponseEntity<BaseResponse> createAppUserFieldMap(@Valid @RequestBody AppUserFieldMapDTO appUserFieldMapDTO) throws URISyntaxException {
        log.debug("REST request to save AppUserFieldMap : {}", appUserFieldMapDTO);
        if (appUserFieldMapDTO.getId() != null) {
            throw new CustomException(ErrorConstants.ENTITY_CREATE_ID_ERROR, ENTITY_NAME);
        }
        AppUserFieldMapDTO result = appUserFieldMapService.save(appUserFieldMapDTO);
        return ResponseEntity.created(new URI("/api/app-user-field-maps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(new BaseResponse(result));
    }

    /**
     * {@code PUT  /app-user-field-maps} : Updates an existing appUserFieldMap.
     *
     * @param appUserFieldMapDTO the appUserFieldMapDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated appUserFieldMapDTO,
     * or with status {@code 400 (Bad Request)} if the appUserFieldMapDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the appUserFieldMapDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/app-user-field-maps")
    public ResponseEntity<BaseResponse> updateAppUserFieldMap(@Valid @RequestBody AppUserFieldMapDTO appUserFieldMapDTO) throws URISyntaxException {
        log.debug("REST request to update AppUserFieldMap : {}", appUserFieldMapDTO);
        if (appUserFieldMapDTO.getId() == null) {
            throw new CustomException(ErrorConstants.ENTITY_FETCH_MISSING_ID_ERROR, ENTITY_NAME);
        }
        AppUserFieldMapDTO result = appUserFieldMapService.save(appUserFieldMapDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, appUserFieldMapDTO.getId().toString()))
            .body(new BaseResponse(result));
    }

    /**
     * {@code GET  /app-user-field-maps} : get all the appUserFieldMaps.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of appUserFieldMaps in body.
     */
    @GetMapping("/app-user-field-maps")
    public ResponseEntity<BaseResponse> getAllAppUserFieldMaps(Pageable pageable) {
        log.debug("REST request to get a page of AppUserFieldMaps");
        Page<AppUserFieldMapDTO> page = appUserFieldMapService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(new BaseResponse(page.getContent()));
    }

    /**
     * {@code GET  /app-user-field-maps/:id} : get the "id" appUserFieldMap.
     *
     * @param id the id of the appUserFieldMapDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the appUserFieldMapDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/app-user-field-maps/{id}")
    public ResponseEntity<BaseResponse> getAppUserFieldMap(@PathVariable Long id) {
        log.debug("REST request to get AppUserFieldMap : {}", id);
        Optional<AppUserFieldMapDTO> appUserFieldMapDTO = appUserFieldMapService.findOne(id);
        return ResponseUtil.wrapOrNotFound(appUserFieldMapDTO.map(BaseResponse::new));
    }

    /**
     * {@code DELETE  /app-user-field-maps/:id} : delete the "id" appUserFieldMap.
     *
     * @param id the id of the appUserFieldMapDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/app-user-field-maps/{id}")
    public ResponseEntity<Void> deleteAppUserFieldMap(@PathVariable Long id) {
        log.debug("REST request to delete AppUserFieldMap : {}", id);
        appUserFieldMapService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
