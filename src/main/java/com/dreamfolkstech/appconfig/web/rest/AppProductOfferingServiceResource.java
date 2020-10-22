package com.dreamfolkstech.appconfig.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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

import com.dreamfolkstech.appconfig.service.AppProductOfferingServiceService;
import com.dreamfolkstech.appconfig.service.dto.AppProductOfferingServiceDTO;
import com.dreamfolkstech.appconfig.web.rest.errors.ErrorConstants;
import com.dreamfolkstech.common.dto.BaseResponse;
import com.dreamfolkstech.common.errors.CustomException;
import com.dreamfolkstech.common.errors.ExternalBaseResponse;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing
 * {@link com.dreamfolkstech.appconfig.domain.AppProductOfferingService}.
 */
@RestController
@RequestMapping("/api")
public class AppProductOfferingServiceResource {

	private final Logger log = LoggerFactory.getLogger(AppProductOfferingServiceResource.class);

	private static final String ENTITY_NAME = "AppProductService";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final AppProductOfferingServiceService appProductOfferingServiceService;

	public AppProductOfferingServiceResource(AppProductOfferingServiceService appProductOfferingServiceService) {
		this.appProductOfferingServiceService = appProductOfferingServiceService;
	}

	/**
	 * {@code POST  /app-product-services} : Create a new appProductService.
	 *
	 * @param appProductOfferingServiceDTO the appProductServiceDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new appProductServiceDTO, or with status
	 *         {@code 400 (Bad Request)} if the appProductService has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/app-product-services")
	public ResponseEntity<BaseResponse> createAppProductService(@RequestBody AppProductOfferingServiceDTO appProductOfferingServiceDTO)
			throws URISyntaxException {
		log.debug("REST request to save AppProductService : {}", appProductOfferingServiceDTO);
		if (appProductOfferingServiceDTO.getId() != null) {
			throw new CustomException(ErrorConstants.ENTITY_CREATE_ID_ERROR, ENTITY_NAME);
		}
		AppProductOfferingServiceDTO result = appProductOfferingServiceService.save(appProductOfferingServiceDTO);
		return ResponseEntity
				.created(new URI("/api/app-product-services/" + result.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
				.body(new BaseResponse(result));
	}

	/**
	 * {@code PUT  /app-product-services} : Updates an existing appProductService.
	 *
	 * @param appProductOfferingServiceDTO the appProductServiceDTO to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated appProductServiceDTO, or with status
	 *         {@code 400 (Bad Request)} if the appProductServiceDTO is not valid,
	 *         or with status {@code 500 (Internal Server Error)} if the
	 *         appProductServiceDTO couldn't be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/app-product-services")
	public ResponseEntity<BaseResponse> updateAppProductService(@RequestBody AppProductOfferingServiceDTO appProductOfferingServiceDTO)
			throws URISyntaxException {
		log.debug("REST request to update AppProductService : {}", appProductOfferingServiceDTO);
		if (appProductOfferingServiceDTO.getId() == null) {
			throw new CustomException(ErrorConstants.ENTITY_FETCH_MISSING_ID_ERROR, ENTITY_NAME);
		}
		AppProductOfferingServiceDTO result = appProductOfferingServiceService.save(appProductOfferingServiceDTO);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
				appProductOfferingServiceDTO.getId().toString())).body(new BaseResponse(result));
	}

	/**
	 * {@code GET  /app-product-services} : get all the appProductServices.
	 *
	 * @param pageable the pagination information.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of appProductServices in body.
	 */
	@GetMapping("/app-product-services")
	public ResponseEntity<BaseResponse> getAllAppProductServices(Pageable pageable) {
		log.debug("REST request to get a page of AppProductServices");
		Page<AppProductOfferingServiceDTO> page = appProductOfferingServiceService.findAll(pageable);
		HttpHeaders headers = PaginationUtil
				.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return ResponseEntity.ok().headers(headers).body(new BaseResponse(page.getContent()));
	}

	/**
	 * {@code GET  /app-product-services/:id} : get the "id" appProductService.
	 *
	 * @param id the id of the appProductServiceDTO to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the appProductServiceDTO, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/app-product-services/{id}")
	public ResponseEntity<BaseResponse> getAppProductService(@PathVariable Long id) {
		log.debug("REST request to get AppProductService : {}", id);
		Optional<AppProductOfferingServiceDTO> appProductOfferingServiceDTO = appProductOfferingServiceService.findOne(id);
		return ResponseUtil.wrapOrNotFound(appProductOfferingServiceDTO.map(BaseResponse::new));
	}

	/**
	 * {@code DELETE  /app-product-services/:id} : delete the "id"
	 * appProductService.
	 *
	 * @param id the id of the appProductServiceDTO to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/app-product-services/{id}")
	public ResponseEntity<Void> deleteAppProductService(@PathVariable Long id) {
		log.debug("REST request to delete AppProductService : {}", id);
		appProductOfferingServiceService.delete(id);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
				.build();
	}
	
}
