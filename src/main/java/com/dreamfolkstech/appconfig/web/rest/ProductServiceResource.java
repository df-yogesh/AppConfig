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

import com.dreamfolkstech.appconfig.service.ProductServiceService;
import com.dreamfolkstech.appconfig.service.dto.ProductServiceDTO;
import com.dreamfolkstech.appconfig.web.rest.errors.ErrorConstants;
import com.dreamfolkstech.common.dto.BaseResponse;
import com.dreamfolkstech.common.errors.CustomException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.dreamfolkstech.appconfig.domain.ProductService}.
 */
@RestController
@RequestMapping("/api")
public class ProductServiceResource {

    private final Logger log = LoggerFactory.getLogger(ProductServiceResource.class);

    private static final String ENTITY_NAME = "ProductService";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductServiceService productServiceService;

    public ProductServiceResource(ProductServiceService productServiceService) {
        this.productServiceService = productServiceService;
    }

    /**
     * {@code POST  /product-services} : Create a new productService.
     *
     * @param productServiceDTO the productServiceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productServiceDTO, or with status {@code 400 (Bad Request)} if the productService has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-services")
    public ResponseEntity<BaseResponse> createProductService(@Valid @RequestBody ProductServiceDTO productServiceDTO) throws URISyntaxException {
        log.debug("REST request to save ProductService : {}", productServiceDTO);
        if (productServiceDTO.getId() != null) {
            throw new CustomException(ErrorConstants.ENTITY_CREATE_ID_ERROR, ENTITY_NAME);
        }
        ProductServiceDTO result = productServiceService.save(productServiceDTO);
        return ResponseEntity.created(new URI("/api/product-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(new BaseResponse(result));
    }

    /**
     * {@code PUT  /product-services} : Updates an existing productService.
     *
     * @param productServiceDTO the productServiceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productServiceDTO,
     * or with status {@code 400 (Bad Request)} if the productServiceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productServiceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-services")
    public ResponseEntity<BaseResponse> updateProductService(@Valid @RequestBody ProductServiceDTO productServiceDTO) throws URISyntaxException {
        log.debug("REST request to update ProductService : {}", productServiceDTO);
        if (productServiceDTO.getId() == null) {
            throw new CustomException(ErrorConstants.ENTITY_FETCH_MISSING_ID_ERROR, ENTITY_NAME);
        }
        ProductServiceDTO result = productServiceService.save(productServiceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, productServiceDTO.getId().toString()))
            .body(new BaseResponse(result));
    }

    /**
     * {@code GET  /product-services} : get all the productServices.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productServices in body.
     */
    @GetMapping("/product-services")
    public ResponseEntity<BaseResponse> getAllProductServices(Pageable pageable) {
        log.debug("REST request to get a page of ProductServices");
        Page<ProductServiceDTO> page = productServiceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(new BaseResponse(page.getContent()));
    }

    /**
     * {@code GET  /product-services/:id} : get the "id" productService.
     *
     * @param id the id of the productServiceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productServiceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-services/{id}")
    public ResponseEntity<BaseResponse> getProductService(@PathVariable Long id) {
        log.debug("REST request to get ProductService : {}", id);
        Optional<ProductServiceDTO> productServiceDTO = productServiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productServiceDTO.map(BaseResponse::new));
    }

    /**
     * {@code DELETE  /product-services/:id} : delete the "id" productService.
     *
     * @param id the id of the productServiceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-services/{id}")
    public ResponseEntity<Void> deleteProductService(@PathVariable Long id) {
        log.debug("REST request to delete ProductService : {}", id);
        productServiceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
