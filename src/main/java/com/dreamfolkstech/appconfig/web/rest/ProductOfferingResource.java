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

import com.dreamfolkstech.appconfig.service.ProductOfferingService;
import com.dreamfolkstech.appconfig.service.dto.ProductOfferingDTO;
import com.dreamfolkstech.appconfig.web.rest.errors.ErrorConstants;
import com.dreamfolkstech.common.dto.BaseResponse;
import com.dreamfolkstech.common.errors.CustomException;
import com.dreamfolkstech.common.errors.ExternalBaseResponse;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.dreamfolkstech.appconfig.domain.ProductOffering}.
 */
@RestController
@RequestMapping("/api")
public class ProductOfferingResource {

    private final Logger log = LoggerFactory.getLogger(ProductOfferingResource.class);

    private static final String ENTITY_NAME = "ProductOffering";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductOfferingService productOfferingService;

    public ProductOfferingResource(ProductOfferingService productOfferingService) {
        this.productOfferingService = productOfferingService;
    }

    /**
     * {@code POST  /product-offerings} : Create a new productOffering.
     *
     * @param productOfferingDTO the productOfferingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productOfferingDTO, or with status {@code 400 (Bad Request)} if the productOffering has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-offerings")
    public ResponseEntity<BaseResponse> createProductOffering(@Valid @RequestBody ProductOfferingDTO productOfferingDTO) throws URISyntaxException {
        log.debug("REST request to save ProductOffering : {}", productOfferingDTO);
        if (productOfferingDTO.getId() != null) {
            throw new CustomException(ErrorConstants.ENTITY_CREATE_ID_ERROR, ENTITY_NAME);
        }
        ProductOfferingDTO result = productOfferingService.save(productOfferingDTO);
        return ResponseEntity.created(new URI("/api/product-offerings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(new BaseResponse(result));
    }

    /**
     * {@code PUT  /product-offerings} : Updates an existing productOffering.
     *
     * @param productOfferingDTO the productOfferingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productOfferingDTO,
     * or with status {@code 400 (Bad Request)} if the productOfferingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productOfferingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-offerings")
    public ResponseEntity<BaseResponse> updateProductOffering(@Valid @RequestBody ProductOfferingDTO productOfferingDTO) throws URISyntaxException {
        log.debug("REST request to update ProductOffering : {}", productOfferingDTO);
        if (productOfferingDTO.getId() == null) {
            throw new CustomException(ErrorConstants.ENTITY_FETCH_MISSING_ID_ERROR, ENTITY_NAME);
        }
        ProductOfferingDTO result = productOfferingService.save(productOfferingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, productOfferingDTO.getId().toString()))
            .body(new BaseResponse(result));
    }

    /**
     * {@code GET  /product-offerings} : get all the productOfferings.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productOfferings in body.
     */
    @GetMapping("/product-offerings")
    public ResponseEntity<BaseResponse> getAllProductOfferings(Pageable pageable) {
        log.debug("REST request to get a page of ProductOfferings");
        Page<ProductOfferingDTO> page = productOfferingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(new BaseResponse(page.getContent()));
    }

    /**
     * {@code GET  /product-offerings/:id} : get the "id" productOffering.
     *
     * @param id the id of the productOfferingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productOfferingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-offerings/{id}")
    public ResponseEntity<BaseResponse> getProductOffering(@PathVariable Long id) {
        log.debug("REST request to get ProductOffering : {}", id);
        Optional<ProductOfferingDTO> productOfferingDTO = productOfferingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productOfferingDTO.map(BaseResponse::new));
    }

    /**
     * {@code DELETE  /product-offerings/:id} : delete the "id" productOffering.
     *
     * @param id the id of the productOfferingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-offerings/{id}")
    public ResponseEntity<Void> deleteProductOffering(@PathVariable Long id) {
        log.debug("REST request to delete ProductOffering : {}", id);
        productOfferingService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
    
    /**
	 * {@code GET /app-product-services/{code}} : Api to get all product service by
	 * product code
	 * @param id
	 * @return
	 */
	@GetMapping("/app-product-services/code/{code}")
	public ResponseEntity<ExternalBaseResponse> getAppProductServiceByAppCode(@PathVariable String code) {
		log.debug("REST request to get product offering By appCode: {}", code);
		ExternalBaseResponse externalBaseResponse = productOfferingService.findAllByAppCode(code);
		return ResponseEntity.ok().body(externalBaseResponse);
	}
}
