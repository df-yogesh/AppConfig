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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dreamfolkstech.appconfig.service.AppConfigService;
import com.dreamfolkstech.appconfig.service.dto.AppConfigDTO;
import com.dreamfolkstech.appconfig.web.rest.errors.ErrorConstants;
import com.dreamfolkstech.common.dto.BaseResponse;
import com.dreamfolkstech.common.errors.CustomException;
import com.dreamfolkstech.common.errors.ExternalBaseResponse;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.dreamfolkstech.appconfig.domain.AppConfig}.
 */
@RestController
@RequestMapping("/api")
public class AppConfigResource {

    private final Logger log = LoggerFactory.getLogger(AppConfigResource.class);

    private static final String ENTITY_NAME = "AppConfig";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AppConfigService appConfigService;

    public AppConfigResource(AppConfigService appConfigService) {
        this.appConfigService = appConfigService;
    }

    /**
     * {@code POST  /app-configs} : Create a new appConfig.
     *
     * @param appConfigDTO the appConfigDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new appConfigDTO, or with status {@code 400 (Bad Request)} if the appConfig has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/app-configs")
    public ResponseEntity<BaseResponse> createAppConfig(@Valid @RequestBody AppConfigDTO appConfigDTO) throws URISyntaxException {
        log.debug("REST request to save AppConfig : {}", appConfigDTO);
        if (appConfigDTO.getId() != null) {
            throw new CustomException(ErrorConstants.ENTITY_CREATE_ID_ERROR, ENTITY_NAME);
        }
        AppConfigDTO result = appConfigService.save(appConfigDTO);
        return ResponseEntity.created(new URI("/api/app-configs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(new BaseResponse(result));
    }

    /**
     * {@code PUT  /app-configs} : Updates an existing appConfig.
     *
     * @param appConfigDTO the appConfigDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated appConfigDTO,
     * or with status {@code 400 (Bad Request)} if the appConfigDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the appConfigDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/app-configs")
    public ResponseEntity<BaseResponse> updateAppConfig(@Valid @RequestBody AppConfigDTO appConfigDTO) throws URISyntaxException {
        log.debug("REST request to update AppConfig : {}", appConfigDTO);
        if (appConfigDTO.getId() == null) {
            throw new CustomException(ErrorConstants.ENTITY_FETCH_MISSING_ID_ERROR, ENTITY_NAME);
        }
        AppConfigDTO result = appConfigService.save(appConfigDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, appConfigDTO.getId().toString()))
            .body(new BaseResponse(result));
    }

    /**
     * {@code GET  /app-configs} : get all the appConfigs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of appConfigs in body.
     */
    @GetMapping("/app-configs")
    public ResponseEntity<BaseResponse> getAllAppConfigs(Pageable pageable) {
        log.debug("REST request to get a page of AppConfigs");
        Page<AppConfigDTO> page = appConfigService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(new BaseResponse(page.getContent()));
    }

    /**
     * {@code GET  /app-configs/:id} : get the "id" appConfig.
     *
     * @param id the id of the appConfigDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the appConfigDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/app-configs/{id}")
    public ResponseEntity<BaseResponse> getAppConfig(@PathVariable Long id) {
        log.debug("REST request to get AppConfig : {}", id);
        Optional<AppConfigDTO> appConfigDTO = appConfigService.findOne(id);
        return ResponseUtil.wrapOrNotFound(appConfigDTO.map(BaseResponse::new));
    }

    /**
     * {@code DELETE  /app-configs/:id} : delete the "id" appConfig.
     *
     * @param id the id of the appConfigDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/app-configs/{id}")
    public ResponseEntity<Void> deleteAppConfig(@PathVariable Long id) {
        log.debug("REST request to delete AppConfig : {}", id);
        appConfigService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
    
   	/**{@code GET /app-configs/all/bycode/{code}} : Api to get all enabled appConfigs by appcode and time
   	 * @param di deviceInfo
   	 * @param requestTime
   	 * @return
   	 */
   	@GetMapping("/app-configs/all/bycode/{code}")
   	public ResponseEntity<ExternalBaseResponse> getAppProductServiceByProductCode(@RequestHeader("di") Optional<String> deviceInfo,
   			@PathVariable String code,@RequestParam Optional<Long> requestTime) {
   		log.debug("REST request to get App Product Service By app Code");
   		ExternalBaseResponse externalBaseResponse = appConfigService.findAllByProductCode(code,requestTime,deviceInfo);
   		return ResponseEntity.ok().body(externalBaseResponse);
   	}
}
