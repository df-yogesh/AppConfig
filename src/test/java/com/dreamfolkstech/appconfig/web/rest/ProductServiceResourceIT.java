package com.dreamfolkstech.appconfig.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.dreamfolkstech.appconfig.AppConfigApp;
import com.dreamfolkstech.appconfig.config.SecurityBeanOverrideConfiguration;
import com.dreamfolkstech.appconfig.domain.ProductOfferingService;
import com.dreamfolkstech.appconfig.repository.ProductOfferingServiceRepository;
import com.dreamfolkstech.appconfig.service.ProductOfferingServiceService;
import com.dreamfolkstech.appconfig.service.dto.ProductOfferingServiceDTO;
import com.dreamfolkstech.appconfig.service.mapper.ProductOfferingServiceMapper;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
/**
 * Integration tests for the {@link ProductOfferingServiceResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, AppConfigApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class ProductServiceResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final GenericStatus DEFAULT_STATUS = GenericStatus.ENABLED;
    private static final GenericStatus UPDATED_STATUS = GenericStatus.DISABLED;

    @Autowired
    private ProductOfferingServiceRepository productOfferingServiceRepository;

    @Autowired
    private ProductOfferingServiceMapper productOfferingServiceMapper;

    @Autowired
    private ProductOfferingServiceService productOfferingServiceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProductServiceMockMvc;

    private ProductOfferingService productOfferingService;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductOfferingService createEntity(EntityManager em) {
        ProductOfferingService productOfferingService = new ProductOfferingService()
            .name(DEFAULT_NAME)
            .code(DEFAULT_CODE)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS);
        return productOfferingService;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductOfferingService createUpdatedEntity(EntityManager em) {
        ProductOfferingService productOfferingService = new ProductOfferingService()
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS);
        return productOfferingService;
    }

    @BeforeEach
    public void initTest() {
        productOfferingService = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductService() throws Exception {
        int databaseSizeBeforeCreate = productOfferingServiceRepository.findAll().size();
        // Create the ProductService
        ProductOfferingServiceDTO productOfferingServiceDTO = productOfferingServiceMapper.toDto(productOfferingService);
        restProductServiceMockMvc.perform(post("/api/product-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productOfferingServiceDTO)))
            .andExpect(status().isCreated());

        // Validate the ProductService in the database
        List<ProductOfferingService> productServiceList = productOfferingServiceRepository.findAll();
        assertThat(productServiceList).hasSize(databaseSizeBeforeCreate + 1);
        ProductOfferingService testProductService = productServiceList.get(productServiceList.size() - 1);
        assertThat(testProductService.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProductService.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testProductService.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testProductService.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createProductServiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productOfferingServiceRepository.findAll().size();

        // Create the ProductService with an existing ID
        productOfferingService.setId(1L);
        ProductOfferingServiceDTO productOfferingServiceDTO = productOfferingServiceMapper.toDto(productOfferingService);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductServiceMockMvc.perform(post("/api/product-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productOfferingServiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductService in the database
        List<ProductOfferingService> productServiceList = productOfferingServiceRepository.findAll();
        assertThat(productServiceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = productOfferingServiceRepository.findAll().size();
        // set the field null
        productOfferingService.setName(null);

        // Create the ProductService, which fails.
        ProductOfferingServiceDTO productOfferingServiceDTO = productOfferingServiceMapper.toDto(productOfferingService);


        restProductServiceMockMvc.perform(post("/api/product-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productOfferingServiceDTO)))
            .andExpect(status().isBadRequest());

        List<ProductOfferingService> productServiceList = productOfferingServiceRepository.findAll();
        assertThat(productServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = productOfferingServiceRepository.findAll().size();
        // set the field null
        productOfferingService.setCode(null);

        // Create the ProductService, which fails.
        ProductOfferingServiceDTO productOfferingServiceDTO = productOfferingServiceMapper.toDto(productOfferingService);


        restProductServiceMockMvc.perform(post("/api/product-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productOfferingServiceDTO)))
            .andExpect(status().isBadRequest());

        List<ProductOfferingService> productServiceList = productOfferingServiceRepository.findAll();
        assertThat(productServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProductServices() throws Exception {
        // Initialize the database
        productOfferingServiceRepository.saveAndFlush(productOfferingService);

        // Get all the productServiceList
        restProductServiceMockMvc.perform(get("/api/product-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productOfferingService.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getProductService() throws Exception {
        // Initialize the database
        productOfferingServiceRepository.saveAndFlush(productOfferingService);

        // Get the productService
        restProductServiceMockMvc.perform(get("/api/product-services/{id}", productOfferingService.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productOfferingService.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingProductService() throws Exception {
        // Get the productService
        restProductServiceMockMvc.perform(get("/api/product-services/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductService() throws Exception {
        // Initialize the database
        productOfferingServiceRepository.saveAndFlush(productOfferingService);

        int databaseSizeBeforeUpdate = productOfferingServiceRepository.findAll().size();

        // Update the productService
        ProductOfferingService updatedProductService = productOfferingServiceRepository.findById(productOfferingService.getId()).get();
        // Disconnect from session so that the updates on updatedProductService are not directly saved in db
        em.detach(updatedProductService);
        updatedProductService
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS);
        ProductOfferingServiceDTO productOfferingServiceDTO = productOfferingServiceMapper.toDto(updatedProductService);

        restProductServiceMockMvc.perform(put("/api/product-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productOfferingServiceDTO)))
            .andExpect(status().isOk());

        // Validate the ProductService in the database
        List<ProductOfferingService> productServiceList = productOfferingServiceRepository.findAll();
        assertThat(productServiceList).hasSize(databaseSizeBeforeUpdate);
        ProductOfferingService testProductService = productServiceList.get(productServiceList.size() - 1);
        assertThat(testProductService.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProductService.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testProductService.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testProductService.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingProductService() throws Exception {
        int databaseSizeBeforeUpdate = productOfferingServiceRepository.findAll().size();

        // Create the ProductService
        ProductOfferingServiceDTO productOfferingServiceDTO = productOfferingServiceMapper.toDto(productOfferingService);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductServiceMockMvc.perform(put("/api/product-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productOfferingServiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductService in the database
        List<ProductOfferingService> productServiceList = productOfferingServiceRepository.findAll();
        assertThat(productServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProductService() throws Exception {
        // Initialize the database
        productOfferingServiceRepository.saveAndFlush(productOfferingService);

        int databaseSizeBeforeDelete = productOfferingServiceRepository.findAll().size();

        // Delete the productService
        restProductServiceMockMvc.perform(delete("/api/product-services/{id}", productOfferingService.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductOfferingService> productServiceList = productOfferingServiceRepository.findAll();
        assertThat(productServiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
