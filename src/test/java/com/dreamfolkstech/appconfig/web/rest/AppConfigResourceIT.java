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
import com.dreamfolkstech.appconfig.domain.AppConfig;
import com.dreamfolkstech.appconfig.repository.AppConfigRepository;
import com.dreamfolkstech.appconfig.service.AppConfigService;
import com.dreamfolkstech.appconfig.service.dto.AppConfigDTO;
import com.dreamfolkstech.appconfig.service.mapper.AppConfigMapper;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
/**
 * Integration tests for the {@link AppConfigResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, AppConfigApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class AppConfigResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final GenericStatus DEFAULT_STATUS = GenericStatus.ENABLED;
    private static final GenericStatus UPDATED_STATUS = GenericStatus.DISABLED;

    @Autowired
    private AppConfigRepository appConfigRepository;

    @Autowired
    private AppConfigMapper appConfigMapper;

    @Autowired
    private AppConfigService appConfigService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAppConfigMockMvc;

    private AppConfig appConfig;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppConfig createEntity(EntityManager em) {
        AppConfig appConfig = new AppConfig()
            .name(DEFAULT_NAME)
            .code(DEFAULT_CODE)
            .value(DEFAULT_VALUE)
            .status(DEFAULT_STATUS);
        return appConfig;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppConfig createUpdatedEntity(EntityManager em) {
        AppConfig appConfig = new AppConfig()
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .value(UPDATED_VALUE)
            .status(UPDATED_STATUS);
        return appConfig;
    }

    @BeforeEach
    public void initTest() {
        appConfig = createEntity(em);
    }

    @Test
    @Transactional
    public void createAppConfig() throws Exception {
        int databaseSizeBeforeCreate = appConfigRepository.findAll().size();
        // Create the AppConfig
        AppConfigDTO appConfigDTO = appConfigMapper.toDto(appConfig);
        restAppConfigMockMvc.perform(post("/api/app-configs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appConfigDTO)))
            .andExpect(status().isCreated());

        // Validate the AppConfig in the database
        List<AppConfig> appConfigList = appConfigRepository.findAll();
        assertThat(appConfigList).hasSize(databaseSizeBeforeCreate + 1);
        AppConfig testAppConfig = appConfigList.get(appConfigList.size() - 1);
        assertThat(testAppConfig.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAppConfig.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testAppConfig.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testAppConfig.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createAppConfigWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = appConfigRepository.findAll().size();

        // Create the AppConfig with an existing ID
        appConfig.setId(1L);
        AppConfigDTO appConfigDTO = appConfigMapper.toDto(appConfig);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAppConfigMockMvc.perform(post("/api/app-configs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appConfigDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AppConfig in the database
        List<AppConfig> appConfigList = appConfigRepository.findAll();
        assertThat(appConfigList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = appConfigRepository.findAll().size();
        // set the field null
        appConfig.setName(null);

        // Create the AppConfig, which fails.
        AppConfigDTO appConfigDTO = appConfigMapper.toDto(appConfig);


        restAppConfigMockMvc.perform(post("/api/app-configs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appConfigDTO)))
            .andExpect(status().isBadRequest());

        List<AppConfig> appConfigList = appConfigRepository.findAll();
        assertThat(appConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = appConfigRepository.findAll().size();
        // set the field null
        appConfig.setCode(null);

        // Create the AppConfig, which fails.
        AppConfigDTO appConfigDTO = appConfigMapper.toDto(appConfig);


        restAppConfigMockMvc.perform(post("/api/app-configs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appConfigDTO)))
            .andExpect(status().isBadRequest());

        List<AppConfig> appConfigList = appConfigRepository.findAll();
        assertThat(appConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAppConfigs() throws Exception {
        // Initialize the database
        appConfigRepository.saveAndFlush(appConfig);

        // Get all the appConfigList
        restAppConfigMockMvc.perform(get("/api/app-configs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appConfig.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getAppConfig() throws Exception {
        // Initialize the database
        appConfigRepository.saveAndFlush(appConfig);

        // Get the appConfig
        restAppConfigMockMvc.perform(get("/api/app-configs/{id}", appConfig.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(appConfig.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAppConfig() throws Exception {
        // Get the appConfig
        restAppConfigMockMvc.perform(get("/api/app-configs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAppConfig() throws Exception {
        // Initialize the database
        appConfigRepository.saveAndFlush(appConfig);

        int databaseSizeBeforeUpdate = appConfigRepository.findAll().size();

        // Update the appConfig
        AppConfig updatedAppConfig = appConfigRepository.findById(appConfig.getId()).get();
        // Disconnect from session so that the updates on updatedAppConfig are not directly saved in db
        em.detach(updatedAppConfig);
        updatedAppConfig
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .value(UPDATED_VALUE)
            .status(UPDATED_STATUS);
        AppConfigDTO appConfigDTO = appConfigMapper.toDto(updatedAppConfig);

        restAppConfigMockMvc.perform(put("/api/app-configs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appConfigDTO)))
            .andExpect(status().isOk());

        // Validate the AppConfig in the database
        List<AppConfig> appConfigList = appConfigRepository.findAll();
        assertThat(appConfigList).hasSize(databaseSizeBeforeUpdate);
        AppConfig testAppConfig = appConfigList.get(appConfigList.size() - 1);
        assertThat(testAppConfig.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAppConfig.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testAppConfig.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testAppConfig.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingAppConfig() throws Exception {
        int databaseSizeBeforeUpdate = appConfigRepository.findAll().size();

        // Create the AppConfig
        AppConfigDTO appConfigDTO = appConfigMapper.toDto(appConfig);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAppConfigMockMvc.perform(put("/api/app-configs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appConfigDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AppConfig in the database
        List<AppConfig> appConfigList = appConfigRepository.findAll();
        assertThat(appConfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAppConfig() throws Exception {
        // Initialize the database
        appConfigRepository.saveAndFlush(appConfig);

        int databaseSizeBeforeDelete = appConfigRepository.findAll().size();

        // Delete the appConfig
        restAppConfigMockMvc.perform(delete("/api/app-configs/{id}", appConfig.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AppConfig> appConfigList = appConfigRepository.findAll();
        assertThat(appConfigList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
