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
import com.dreamfolkstech.appconfig.domain.AppProductOfferingService;
import com.dreamfolkstech.appconfig.repository.AppProductOfferingServiceRepository;
import com.dreamfolkstech.appconfig.service.AppProductOfferingServiceService;
import com.dreamfolkstech.appconfig.service.dto.AppProductOfferingServiceDTO;
import com.dreamfolkstech.appconfig.service.mapper.AppProductOfferingServiceMapper;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
/**
 * Integration tests for the {@link AppProductOfferingServiceResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, AppConfigApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class AppProductOfferingServiceResourceIT {

    private static final Integer DEFAULT_PRODUCT_SERVICE_ID = 1;
    private static final Integer UPDATED_PRODUCT_SERVICE_ID = 2;

    private static final GenericStatus DEFAULT_STATUS = GenericStatus.ENABLED;
    private static final GenericStatus UPDATED_STATUS = GenericStatus.DISABLED;

    @Autowired
    private AppProductOfferingServiceRepository appProductOfferingServiceRepository;

    @Autowired
    private AppProductOfferingServiceMapper appProductOfferingServiceMapper;

    @Autowired
    private AppProductOfferingServiceService appProductOfferingServiceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAppProductServiceMockMvc;

    private AppProductOfferingService appProductOfferingService;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppProductOfferingService createEntity(EntityManager em) {
        AppProductOfferingService appProductOfferingService = new AppProductOfferingService()
            .productOfferingServiceId(DEFAULT_PRODUCT_SERVICE_ID)
            .status(DEFAULT_STATUS);
        return appProductOfferingService;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppProductOfferingService createUpdatedEntity(EntityManager em) {
        AppProductOfferingService appProductOfferingService = new AppProductOfferingService()
            .productOfferingServiceId(UPDATED_PRODUCT_SERVICE_ID)
            .status(UPDATED_STATUS);
        return appProductOfferingService;
    }

    @BeforeEach
    public void initTest() {
        appProductOfferingService = createEntity(em);
    }

    @Test
    @Transactional
    public void createAppProductService() throws Exception {
        int databaseSizeBeforeCreate = appProductOfferingServiceRepository.findAll().size();
        // Create the AppProductService
        AppProductOfferingServiceDTO appProductOfferingServiceDTO = appProductOfferingServiceMapper.toDto(appProductOfferingService);
        restAppProductServiceMockMvc.perform(post("/api/app-product-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appProductOfferingServiceDTO)))
            .andExpect(status().isCreated());

        // Validate the AppProductService in the database
        List<AppProductOfferingService> appProductServiceList = appProductOfferingServiceRepository.findAll();
        assertThat(appProductServiceList).hasSize(databaseSizeBeforeCreate + 1);
        AppProductOfferingService testAppProductService = appProductServiceList.get(appProductServiceList.size() - 1);
        assertThat(testAppProductService.getProductOfferingServiceId()).isEqualTo(DEFAULT_PRODUCT_SERVICE_ID);
        assertThat(testAppProductService.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createAppProductServiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = appProductOfferingServiceRepository.findAll().size();

        // Create the AppProductService with an existing ID
        appProductOfferingService.setId(1L);
        AppProductOfferingServiceDTO appProductOfferingServiceDTO = appProductOfferingServiceMapper.toDto(appProductOfferingService);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAppProductServiceMockMvc.perform(post("/api/app-product-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appProductOfferingServiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AppProductService in the database
        List<AppProductOfferingService> appProductServiceList = appProductOfferingServiceRepository.findAll();
        assertThat(appProductServiceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAppProductServices() throws Exception {
        // Initialize the database
        appProductOfferingServiceRepository.saveAndFlush(appProductOfferingService);

        // Get all the appProductServiceList
        restAppProductServiceMockMvc.perform(get("/api/app-product-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appProductOfferingService.getId().intValue())))
            .andExpect(jsonPath("$.[*].productServiceId").value(hasItem(DEFAULT_PRODUCT_SERVICE_ID)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getAppProductService() throws Exception {
        // Initialize the database
        appProductOfferingServiceRepository.saveAndFlush(appProductOfferingService);

        // Get the appProductService
        restAppProductServiceMockMvc.perform(get("/api/app-product-services/{id}", appProductOfferingService.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(appProductOfferingService.getId().intValue()))
            .andExpect(jsonPath("$.productServiceId").value(DEFAULT_PRODUCT_SERVICE_ID))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAppProductService() throws Exception {
        // Get the appProductService
        restAppProductServiceMockMvc.perform(get("/api/app-product-services/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAppProductService() throws Exception {
        // Initialize the database
        appProductOfferingServiceRepository.saveAndFlush(appProductOfferingService);

        int databaseSizeBeforeUpdate = appProductOfferingServiceRepository.findAll().size();

        // Update the appProductService
        AppProductOfferingService updatedAppProductService = appProductOfferingServiceRepository.findById(appProductOfferingService.getId()).get();
        // Disconnect from session so that the updates on updatedAppProductService are not directly saved in db
        em.detach(updatedAppProductService);
        updatedAppProductService
            .productOfferingServiceId(UPDATED_PRODUCT_SERVICE_ID)
            .status(UPDATED_STATUS);
        AppProductOfferingServiceDTO appProductOfferingServiceDTO = appProductOfferingServiceMapper.toDto(updatedAppProductService);

        restAppProductServiceMockMvc.perform(put("/api/app-product-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appProductOfferingServiceDTO)))
            .andExpect(status().isOk());

        // Validate the AppProductService in the database
        List<AppProductOfferingService> appProductServiceList = appProductOfferingServiceRepository.findAll();
        assertThat(appProductServiceList).hasSize(databaseSizeBeforeUpdate);
        AppProductOfferingService testAppProductService = appProductServiceList.get(appProductServiceList.size() - 1);
        assertThat(testAppProductService.getProductOfferingServiceId()).isEqualTo(UPDATED_PRODUCT_SERVICE_ID);
        assertThat(testAppProductService.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingAppProductService() throws Exception {
        int databaseSizeBeforeUpdate = appProductOfferingServiceRepository.findAll().size();

        // Create the AppProductService
        AppProductOfferingServiceDTO appProductOfferingServiceDTO = appProductOfferingServiceMapper.toDto(appProductOfferingService);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAppProductServiceMockMvc.perform(put("/api/app-product-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appProductOfferingServiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AppProductService in the database
        List<AppProductOfferingService> appProductServiceList = appProductOfferingServiceRepository.findAll();
        assertThat(appProductServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAppProductService() throws Exception {
        // Initialize the database
        appProductOfferingServiceRepository.saveAndFlush(appProductOfferingService);

        int databaseSizeBeforeDelete = appProductOfferingServiceRepository.findAll().size();

        // Delete the appProductService
        restAppProductServiceMockMvc.perform(delete("/api/app-product-services/{id}", appProductOfferingService.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AppProductOfferingService> appProductServiceList = appProductOfferingServiceRepository.findAll();
        assertThat(appProductServiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
