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
import com.dreamfolkstech.appconfig.domain.ProductOffering;
import com.dreamfolkstech.appconfig.repository.ProductOfferingRepository;
import com.dreamfolkstech.appconfig.service.ProductOfferingService;
import com.dreamfolkstech.appconfig.service.dto.ProductOfferingDTO;
import com.dreamfolkstech.appconfig.service.mapper.ProductOfferingMapper;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
/**
 * Integration tests for the {@link ProductOfferingResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, AppConfigApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class ProductOfferingResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_SORT_ORDER = 1;
    private static final Integer UPDATED_SORT_ORDER = 2;

    private static final GenericStatus DEFAULT_STATUS = GenericStatus.ENABLED;
    private static final GenericStatus UPDATED_STATUS = GenericStatus.DISABLED;

    @Autowired
    private ProductOfferingRepository productOfferingRepository;

    @Autowired
    private ProductOfferingMapper productOfferingMapper;

    @Autowired
    private ProductOfferingService productOfferingService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProductOfferingMockMvc;

    private ProductOffering productOffering;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductOffering createEntity(EntityManager em) {
        ProductOffering productOffering = new ProductOffering()
            .name(DEFAULT_NAME)
            .code(DEFAULT_CODE)
            .description(DEFAULT_DESCRIPTION)
            .sortOrder(DEFAULT_SORT_ORDER)
            .status(DEFAULT_STATUS);
        return productOffering;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductOffering createUpdatedEntity(EntityManager em) {
        ProductOffering productOffering = new ProductOffering()
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION)
            .sortOrder(UPDATED_SORT_ORDER)
            .status(UPDATED_STATUS);
        return productOffering;
    }

    @BeforeEach
    public void initTest() {
        productOffering = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductOffering() throws Exception {
        int databaseSizeBeforeCreate = productOfferingRepository.findAll().size();
        // Create the ProductOffering
        ProductOfferingDTO productOfferingDTO = productOfferingMapper.toDto(productOffering);
        restProductOfferingMockMvc.perform(post("/api/product-offerings").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productOfferingDTO)))
            .andExpect(status().isCreated());

        // Validate the ProductOffering in the database
        List<ProductOffering> productOfferingList = productOfferingRepository.findAll();
        assertThat(productOfferingList).hasSize(databaseSizeBeforeCreate + 1);
        ProductOffering testProductOffering = productOfferingList.get(productOfferingList.size() - 1);
        assertThat(testProductOffering.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProductOffering.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testProductOffering.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testProductOffering.getSortOrder()).isEqualTo(DEFAULT_SORT_ORDER);
        assertThat(testProductOffering.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createProductOfferingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productOfferingRepository.findAll().size();

        // Create the ProductOffering with an existing ID
        productOffering.setId(1L);
        ProductOfferingDTO productOfferingDTO = productOfferingMapper.toDto(productOffering);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductOfferingMockMvc.perform(post("/api/product-offerings").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productOfferingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductOffering in the database
        List<ProductOffering> productOfferingList = productOfferingRepository.findAll();
        assertThat(productOfferingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = productOfferingRepository.findAll().size();
        // set the field null
        productOffering.setName(null);

        // Create the ProductOffering, which fails.
        ProductOfferingDTO productOfferingDTO = productOfferingMapper.toDto(productOffering);


        restProductOfferingMockMvc.perform(post("/api/product-offerings").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productOfferingDTO)))
            .andExpect(status().isBadRequest());

        List<ProductOffering> productOfferingList = productOfferingRepository.findAll();
        assertThat(productOfferingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = productOfferingRepository.findAll().size();
        // set the field null
        productOffering.setCode(null);

        // Create the ProductOffering, which fails.
        ProductOfferingDTO productOfferingDTO = productOfferingMapper.toDto(productOffering);


        restProductOfferingMockMvc.perform(post("/api/product-offerings").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productOfferingDTO)))
            .andExpect(status().isBadRequest());

        List<ProductOffering> productOfferingList = productOfferingRepository.findAll();
        assertThat(productOfferingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProductOfferings() throws Exception {
        // Initialize the database
        productOfferingRepository.saveAndFlush(productOffering);

        // Get all the productOfferingList
        restProductOfferingMockMvc.perform(get("/api/product-offerings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productOffering.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].sortOrder").value(hasItem(DEFAULT_SORT_ORDER)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getProductOffering() throws Exception {
        // Initialize the database
        productOfferingRepository.saveAndFlush(productOffering);

        // Get the productOffering
        restProductOfferingMockMvc.perform(get("/api/product-offerings/{id}", productOffering.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productOffering.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.sortOrder").value(DEFAULT_SORT_ORDER))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingProductOffering() throws Exception {
        // Get the productOffering
        restProductOfferingMockMvc.perform(get("/api/product-offerings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductOffering() throws Exception {
        // Initialize the database
        productOfferingRepository.saveAndFlush(productOffering);

        int databaseSizeBeforeUpdate = productOfferingRepository.findAll().size();

        // Update the productOffering
        ProductOffering updatedProductOffering = productOfferingRepository.findById(productOffering.getId()).get();
        // Disconnect from session so that the updates on updatedProductOffering are not directly saved in db
        em.detach(updatedProductOffering);
        updatedProductOffering
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION)
            .sortOrder(UPDATED_SORT_ORDER)
            .status(UPDATED_STATUS);
        ProductOfferingDTO productOfferingDTO = productOfferingMapper.toDto(updatedProductOffering);

        restProductOfferingMockMvc.perform(put("/api/product-offerings").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productOfferingDTO)))
            .andExpect(status().isOk());

        // Validate the ProductOffering in the database
        List<ProductOffering> productOfferingList = productOfferingRepository.findAll();
        assertThat(productOfferingList).hasSize(databaseSizeBeforeUpdate);
        ProductOffering testProductOffering = productOfferingList.get(productOfferingList.size() - 1);
        assertThat(testProductOffering.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProductOffering.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testProductOffering.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testProductOffering.getSortOrder()).isEqualTo(UPDATED_SORT_ORDER);
        assertThat(testProductOffering.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingProductOffering() throws Exception {
        int databaseSizeBeforeUpdate = productOfferingRepository.findAll().size();

        // Create the ProductOffering
        ProductOfferingDTO productOfferingDTO = productOfferingMapper.toDto(productOffering);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductOfferingMockMvc.perform(put("/api/product-offerings").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productOfferingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductOffering in the database
        List<ProductOffering> productOfferingList = productOfferingRepository.findAll();
        assertThat(productOfferingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProductOffering() throws Exception {
        // Initialize the database
        productOfferingRepository.saveAndFlush(productOffering);

        int databaseSizeBeforeDelete = productOfferingRepository.findAll().size();

        // Delete the productOffering
        restProductOfferingMockMvc.perform(delete("/api/product-offerings/{id}", productOffering.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductOffering> productOfferingList = productOfferingRepository.findAll();
        assertThat(productOfferingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
