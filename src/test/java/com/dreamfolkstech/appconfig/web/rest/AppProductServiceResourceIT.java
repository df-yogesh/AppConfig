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
import com.dreamfolkstech.appconfig.domain.AppProductService;
import com.dreamfolkstech.appconfig.repository.AppProductServiceRepository;
import com.dreamfolkstech.appconfig.service.AppProductServiceService;
import com.dreamfolkstech.appconfig.service.dto.AppProductServiceDTO;
import com.dreamfolkstech.appconfig.service.mapper.AppProductServiceMapper;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
/**
 * Integration tests for the {@link AppProductServiceResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, AppConfigApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class AppProductServiceResourceIT {

    private static final Integer DEFAULT_PRODUCT_SERVICE_ID = 1;
    private static final Integer UPDATED_PRODUCT_SERVICE_ID = 2;

    private static final GenericStatus DEFAULT_STATUS = GenericStatus.ENABLED;
    private static final GenericStatus UPDATED_STATUS = GenericStatus.DISABLED;

    @Autowired
    private AppProductServiceRepository appProductServiceRepository;

    @Autowired
    private AppProductServiceMapper appProductServiceMapper;

    @Autowired
    private AppProductServiceService appProductServiceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAppProductServiceMockMvc;

    private AppProductService appProductService;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppProductService createEntity(EntityManager em) {
        AppProductService appProductService = new AppProductService()
            .productServiceId(DEFAULT_PRODUCT_SERVICE_ID)
            .status(DEFAULT_STATUS);
        return appProductService;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppProductService createUpdatedEntity(EntityManager em) {
        AppProductService appProductService = new AppProductService()
            .productServiceId(UPDATED_PRODUCT_SERVICE_ID)
            .status(UPDATED_STATUS);
        return appProductService;
    }

    @BeforeEach
    public void initTest() {
        appProductService = createEntity(em);
    }

    @Test
    @Transactional
    public void createAppProductService() throws Exception {
        int databaseSizeBeforeCreate = appProductServiceRepository.findAll().size();
        // Create the AppProductService
        AppProductServiceDTO appProductServiceDTO = appProductServiceMapper.toDto(appProductService);
        restAppProductServiceMockMvc.perform(post("/api/app-product-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appProductServiceDTO)))
            .andExpect(status().isCreated());

        // Validate the AppProductService in the database
        List<AppProductService> appProductServiceList = appProductServiceRepository.findAll();
        assertThat(appProductServiceList).hasSize(databaseSizeBeforeCreate + 1);
        AppProductService testAppProductService = appProductServiceList.get(appProductServiceList.size() - 1);
        assertThat(testAppProductService.getProductServiceId()).isEqualTo(DEFAULT_PRODUCT_SERVICE_ID);
        assertThat(testAppProductService.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createAppProductServiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = appProductServiceRepository.findAll().size();

        // Create the AppProductService with an existing ID
        appProductService.setId(1L);
        AppProductServiceDTO appProductServiceDTO = appProductServiceMapper.toDto(appProductService);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAppProductServiceMockMvc.perform(post("/api/app-product-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appProductServiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AppProductService in the database
        List<AppProductService> appProductServiceList = appProductServiceRepository.findAll();
        assertThat(appProductServiceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAppProductServices() throws Exception {
        // Initialize the database
        appProductServiceRepository.saveAndFlush(appProductService);

        // Get all the appProductServiceList
        restAppProductServiceMockMvc.perform(get("/api/app-product-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appProductService.getId().intValue())))
            .andExpect(jsonPath("$.[*].productServiceId").value(hasItem(DEFAULT_PRODUCT_SERVICE_ID)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getAppProductService() throws Exception {
        // Initialize the database
        appProductServiceRepository.saveAndFlush(appProductService);

        // Get the appProductService
        restAppProductServiceMockMvc.perform(get("/api/app-product-services/{id}", appProductService.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(appProductService.getId().intValue()))
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
        appProductServiceRepository.saveAndFlush(appProductService);

        int databaseSizeBeforeUpdate = appProductServiceRepository.findAll().size();

        // Update the appProductService
        AppProductService updatedAppProductService = appProductServiceRepository.findById(appProductService.getId()).get();
        // Disconnect from session so that the updates on updatedAppProductService are not directly saved in db
        em.detach(updatedAppProductService);
        updatedAppProductService
            .productServiceId(UPDATED_PRODUCT_SERVICE_ID)
            .status(UPDATED_STATUS);
        AppProductServiceDTO appProductServiceDTO = appProductServiceMapper.toDto(updatedAppProductService);

        restAppProductServiceMockMvc.perform(put("/api/app-product-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appProductServiceDTO)))
            .andExpect(status().isOk());

        // Validate the AppProductService in the database
        List<AppProductService> appProductServiceList = appProductServiceRepository.findAll();
        assertThat(appProductServiceList).hasSize(databaseSizeBeforeUpdate);
        AppProductService testAppProductService = appProductServiceList.get(appProductServiceList.size() - 1);
        assertThat(testAppProductService.getProductServiceId()).isEqualTo(UPDATED_PRODUCT_SERVICE_ID);
        assertThat(testAppProductService.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingAppProductService() throws Exception {
        int databaseSizeBeforeUpdate = appProductServiceRepository.findAll().size();

        // Create the AppProductService
        AppProductServiceDTO appProductServiceDTO = appProductServiceMapper.toDto(appProductService);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAppProductServiceMockMvc.perform(put("/api/app-product-services").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appProductServiceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AppProductService in the database
        List<AppProductService> appProductServiceList = appProductServiceRepository.findAll();
        assertThat(appProductServiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAppProductService() throws Exception {
        // Initialize the database
        appProductServiceRepository.saveAndFlush(appProductService);

        int databaseSizeBeforeDelete = appProductServiceRepository.findAll().size();

        // Delete the appProductService
        restAppProductServiceMockMvc.perform(delete("/api/app-product-services/{id}", appProductService.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AppProductService> appProductServiceList = appProductServiceRepository.findAll();
        assertThat(appProductServiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
