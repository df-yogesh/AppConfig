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
import com.dreamfolkstech.appconfig.domain.ProductService;
import com.dreamfolkstech.appconfig.repository.ProductServiceRepository;
import com.dreamfolkstech.appconfig.service.ProductServiceService;
import com.dreamfolkstech.appconfig.service.dto.ProductServiceDTO;
import com.dreamfolkstech.appconfig.service.mapper.ProductServiceMapper;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
/**
 * Integration tests for the {@link ProductServiceResource} REST controller.
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
    private ProductServiceRepository productServiceRepository;

    @Autowired
    private ProductServiceMapper productServiceMapper;

    @Autowired
    private ProductServiceService productServiceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProductServiceMockMvc;

    private ProductService productService;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductService createEntity(EntityManager em) {
        ProductService productService = new ProductService()
            .name(DEFAULT_NAME)
            .code(DEFAULT_CODE)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS);
        return productService;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductService createUpdatedEntity(EntityManager em) {
        ProductService productService = new ProductService()
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS);
        return productService;
    }

    @BeforeEach
    public void initTest() {
        productService = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductService() throws Exception {
        int databaseSizeBeforeCreate = productServiceRepository.findAll().size();
        // Create the ProductService
        ProductServiceDTO productServiceDTO = productServiceMapper.toDto(productService);
        restProductServiceMockMvc.perform(post("/api/product-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productServiceDTO)))
            .andExpect(status().isCreated());

        // Validate the ProductService in the database
        List<ProductService> productServiceList = productServiceRepository.findAll();
        assertThat(productServiceList).hasSize(databaseSizeBeforeCreate + 1);
        ProductService testProductService = productServiceList.get(productServiceList.size() - 1);
        assertThat(testProductService.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProductService.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testProductService.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testProductService.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createProductServiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productServiceRepository.findAll().size();

        // Create the ProductService with an existing ID
        productService.setId(1L);
        ProductServiceDTO productServiceDTO = productServiceMapper.toDto(productService);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductServiceMockMvc.perform(post("/api/product-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productServiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductService in the database
        List<ProductService> productServiceList = productServiceRepository.findAll();
        assertThat(productServiceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = productServiceRepository.findAll().size();
        // set the field null
        productService.setName(null);

        // Create the ProductService, which fails.
        ProductServiceDTO productServiceDTO = productServiceMapper.toDto(productService);


        restProductServiceMockMvc.perform(post("/api/product-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productServiceDTO)))
            .andExpect(status().isBadRequest());

        List<ProductService> productServiceList = productServiceRepository.findAll();
        assertThat(productServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = productServiceRepository.findAll().size();
        // set the field null
        productService.setCode(null);

        // Create the ProductService, which fails.
        ProductServiceDTO productServiceDTO = productServiceMapper.toDto(productService);


        restProductServiceMockMvc.perform(post("/api/product-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productServiceDTO)))
            .andExpect(status().isBadRequest());

        List<ProductService> productServiceList = productServiceRepository.findAll();
        assertThat(productServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProductServices() throws Exception {
        // Initialize the database
        productServiceRepository.saveAndFlush(productService);

        // Get all the productServiceList
        restProductServiceMockMvc.perform(get("/api/product-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productService.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getProductService() throws Exception {
        // Initialize the database
        productServiceRepository.saveAndFlush(productService);

        // Get the productService
        restProductServiceMockMvc.perform(get("/api/product-services/{id}", productService.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productService.getId().intValue()))
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
        productServiceRepository.saveAndFlush(productService);

        int databaseSizeBeforeUpdate = productServiceRepository.findAll().size();

        // Update the productService
        ProductService updatedProductService = productServiceRepository.findById(productService.getId()).get();
        // Disconnect from session so that the updates on updatedProductService are not directly saved in db
        em.detach(updatedProductService);
        updatedProductService
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS);
        ProductServiceDTO productServiceDTO = productServiceMapper.toDto(updatedProductService);

        restProductServiceMockMvc.perform(put("/api/product-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productServiceDTO)))
            .andExpect(status().isOk());

        // Validate the ProductService in the database
        List<ProductService> productServiceList = productServiceRepository.findAll();
        assertThat(productServiceList).hasSize(databaseSizeBeforeUpdate);
        ProductService testProductService = productServiceList.get(productServiceList.size() - 1);
        assertThat(testProductService.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProductService.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testProductService.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testProductService.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingProductService() throws Exception {
        int databaseSizeBeforeUpdate = productServiceRepository.findAll().size();

        // Create the ProductService
        ProductServiceDTO productServiceDTO = productServiceMapper.toDto(productService);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductServiceMockMvc.perform(put("/api/product-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productServiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductService in the database
        List<ProductService> productServiceList = productServiceRepository.findAll();
        assertThat(productServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProductService() throws Exception {
        // Initialize the database
        productServiceRepository.saveAndFlush(productService);

        int databaseSizeBeforeDelete = productServiceRepository.findAll().size();

        // Delete the productService
        restProductServiceMockMvc.perform(delete("/api/product-services/{id}", productService.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductService> productServiceList = productServiceRepository.findAll();
        assertThat(productServiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
