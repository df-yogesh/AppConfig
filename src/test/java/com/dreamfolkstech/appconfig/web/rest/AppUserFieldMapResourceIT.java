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
import com.dreamfolkstech.appconfig.domain.AppUserFieldMap;
import com.dreamfolkstech.appconfig.repository.AppUserFieldMapRepository;
import com.dreamfolkstech.appconfig.service.AppUserFieldMapService;
import com.dreamfolkstech.appconfig.service.dto.AppUserFieldMapDTO;
import com.dreamfolkstech.appconfig.service.mapper.AppUserFieldMapMapper;
import com.dreamfolkstech.common.domain.enumeration.BooleanValue;
/**
 * Integration tests for the {@link AppUserFieldMapResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, AppConfigApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class AppUserFieldMapResourceIT {

    private static final Integer DEFAULT_APP_ID = 1;
    private static final Integer UPDATED_APP_ID = 2;

    private static final String DEFAULT_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_USER_FIELD_CODE = "AAAAAAAAAA";
    private static final String UPDATED_USER_FIELD_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_USER_FIELD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_FIELD_NAME = "BBBBBBBBBB";

    private static final BooleanValue DEFAULT_IS_MANDATORY = BooleanValue.YES;
    private static final BooleanValue UPDATED_IS_MANDATORY = BooleanValue.NO;

    private static final Integer DEFAULT_PROFILE_SCORE = 1;
    private static final Integer UPDATED_PROFILE_SCORE = 2;

    private static final String DEFAULT_VALIDATION_REGEX = "AAAAAAAAAA";
    private static final String UPDATED_VALIDATION_REGEX = "BBBBBBBBBB";

    private static final BooleanValue DEFAULT_REGISTRATION_FIELD = BooleanValue.YES;
    private static final BooleanValue UPDATED_REGISTRATION_FIELD = BooleanValue.NO;

    @Autowired
    private AppUserFieldMapRepository appUserFieldMapRepository;

    @Autowired
    private AppUserFieldMapMapper appUserFieldMapMapper;

    @Autowired
    private AppUserFieldMapService appUserFieldMapService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAppUserFieldMapMockMvc;

    private AppUserFieldMap appUserFieldMap;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppUserFieldMap createEntity(EntityManager em) {
        AppUserFieldMap appUserFieldMap = new AppUserFieldMap()
            .appId(DEFAULT_APP_ID)
            .groupName(DEFAULT_GROUP_NAME)
            .userFieldCode(DEFAULT_USER_FIELD_CODE)
            .userFieldName(DEFAULT_USER_FIELD_NAME)
            .isMandatory(DEFAULT_IS_MANDATORY)
            .profileScore(DEFAULT_PROFILE_SCORE)
            .validationRegex(DEFAULT_VALIDATION_REGEX)
            .registrationField(DEFAULT_REGISTRATION_FIELD);
        return appUserFieldMap;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppUserFieldMap createUpdatedEntity(EntityManager em) {
        AppUserFieldMap appUserFieldMap = new AppUserFieldMap()
            .appId(UPDATED_APP_ID)
            .groupName(UPDATED_GROUP_NAME)
            .userFieldCode(UPDATED_USER_FIELD_CODE)
            .userFieldName(UPDATED_USER_FIELD_NAME)
            .isMandatory(UPDATED_IS_MANDATORY)
            .profileScore(UPDATED_PROFILE_SCORE)
            .validationRegex(UPDATED_VALIDATION_REGEX)
            .registrationField(UPDATED_REGISTRATION_FIELD);
        return appUserFieldMap;
    }

    @BeforeEach
    public void initTest() {
        appUserFieldMap = createEntity(em);
    }

    @Test
    @Transactional
    public void createAppUserFieldMap() throws Exception {
        int databaseSizeBeforeCreate = appUserFieldMapRepository.findAll().size();
        // Create the AppUserFieldMap
        AppUserFieldMapDTO appUserFieldMapDTO = appUserFieldMapMapper.toDto(appUserFieldMap);
        restAppUserFieldMapMockMvc.perform(post("/api/app-user-field-maps").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appUserFieldMapDTO)))
            .andExpect(status().isCreated());

        // Validate the AppUserFieldMap in the database
        List<AppUserFieldMap> appUserFieldMapList = appUserFieldMapRepository.findAll();
        assertThat(appUserFieldMapList).hasSize(databaseSizeBeforeCreate + 1);
        AppUserFieldMap testAppUserFieldMap = appUserFieldMapList.get(appUserFieldMapList.size() - 1);
        assertThat(testAppUserFieldMap.getAppId()).isEqualTo(DEFAULT_APP_ID);
        assertThat(testAppUserFieldMap.getGroupName()).isEqualTo(DEFAULT_GROUP_NAME);
        assertThat(testAppUserFieldMap.getUserFieldCode()).isEqualTo(DEFAULT_USER_FIELD_CODE);
        assertThat(testAppUserFieldMap.getUserFieldName()).isEqualTo(DEFAULT_USER_FIELD_NAME);
        assertThat(testAppUserFieldMap.getIsMandatory()).isEqualTo(DEFAULT_IS_MANDATORY);
        assertThat(testAppUserFieldMap.getProfileScore()).isEqualTo(DEFAULT_PROFILE_SCORE);
        assertThat(testAppUserFieldMap.getValidationRegex()).isEqualTo(DEFAULT_VALIDATION_REGEX);
        assertThat(testAppUserFieldMap.getRegistrationField()).isEqualTo(DEFAULT_REGISTRATION_FIELD);
    }

    @Test
    @Transactional
    public void createAppUserFieldMapWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = appUserFieldMapRepository.findAll().size();

        // Create the AppUserFieldMap with an existing ID
        appUserFieldMap.setId(1L);
        AppUserFieldMapDTO appUserFieldMapDTO = appUserFieldMapMapper.toDto(appUserFieldMap);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAppUserFieldMapMockMvc.perform(post("/api/app-user-field-maps").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appUserFieldMapDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AppUserFieldMap in the database
        List<AppUserFieldMap> appUserFieldMapList = appUserFieldMapRepository.findAll();
        assertThat(appUserFieldMapList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAppUserFieldMaps() throws Exception {
        // Initialize the database
        appUserFieldMapRepository.saveAndFlush(appUserFieldMap);

        // Get all the appUserFieldMapList
        restAppUserFieldMapMockMvc.perform(get("/api/app-user-field-maps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appUserFieldMap.getId().intValue())))
            .andExpect(jsonPath("$.[*].appId").value(hasItem(DEFAULT_APP_ID)))
            .andExpect(jsonPath("$.[*].groupName").value(hasItem(DEFAULT_GROUP_NAME)))
            .andExpect(jsonPath("$.[*].userFieldCode").value(hasItem(DEFAULT_USER_FIELD_CODE)))
            .andExpect(jsonPath("$.[*].userFieldName").value(hasItem(DEFAULT_USER_FIELD_NAME)))
            .andExpect(jsonPath("$.[*].isMandatory").value(hasItem(DEFAULT_IS_MANDATORY.toString())))
            .andExpect(jsonPath("$.[*].profileScore").value(hasItem(DEFAULT_PROFILE_SCORE)))
            .andExpect(jsonPath("$.[*].validationRegex").value(hasItem(DEFAULT_VALIDATION_REGEX)))
            .andExpect(jsonPath("$.[*].registrationField").value(hasItem(DEFAULT_REGISTRATION_FIELD.toString())));
    }
    
    @Test
    @Transactional
    public void getAppUserFieldMap() throws Exception {
        // Initialize the database
        appUserFieldMapRepository.saveAndFlush(appUserFieldMap);

        // Get the appUserFieldMap
        restAppUserFieldMapMockMvc.perform(get("/api/app-user-field-maps/{id}", appUserFieldMap.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(appUserFieldMap.getId().intValue()))
            .andExpect(jsonPath("$.appId").value(DEFAULT_APP_ID))
            .andExpect(jsonPath("$.groupName").value(DEFAULT_GROUP_NAME))
            .andExpect(jsonPath("$.userFieldCode").value(DEFAULT_USER_FIELD_CODE))
            .andExpect(jsonPath("$.userFieldName").value(DEFAULT_USER_FIELD_NAME))
            .andExpect(jsonPath("$.isMandatory").value(DEFAULT_IS_MANDATORY.toString()))
            .andExpect(jsonPath("$.profileScore").value(DEFAULT_PROFILE_SCORE))
            .andExpect(jsonPath("$.validationRegex").value(DEFAULT_VALIDATION_REGEX))
            .andExpect(jsonPath("$.registrationField").value(DEFAULT_REGISTRATION_FIELD.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingAppUserFieldMap() throws Exception {
        // Get the appUserFieldMap
        restAppUserFieldMapMockMvc.perform(get("/api/app-user-field-maps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAppUserFieldMap() throws Exception {
        // Initialize the database
        appUserFieldMapRepository.saveAndFlush(appUserFieldMap);

        int databaseSizeBeforeUpdate = appUserFieldMapRepository.findAll().size();

        // Update the appUserFieldMap
        AppUserFieldMap updatedAppUserFieldMap = appUserFieldMapRepository.findById(appUserFieldMap.getId()).get();
        // Disconnect from session so that the updates on updatedAppUserFieldMap are not directly saved in db
        em.detach(updatedAppUserFieldMap);
        updatedAppUserFieldMap
            .appId(UPDATED_APP_ID)
            .groupName(UPDATED_GROUP_NAME)
            .userFieldCode(UPDATED_USER_FIELD_CODE)
            .userFieldName(UPDATED_USER_FIELD_NAME)
            .isMandatory(UPDATED_IS_MANDATORY)
            .profileScore(UPDATED_PROFILE_SCORE)
            .validationRegex(UPDATED_VALIDATION_REGEX)
            .registrationField(UPDATED_REGISTRATION_FIELD);
        AppUserFieldMapDTO appUserFieldMapDTO = appUserFieldMapMapper.toDto(updatedAppUserFieldMap);

        restAppUserFieldMapMockMvc.perform(put("/api/app-user-field-maps").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appUserFieldMapDTO)))
            .andExpect(status().isOk());

        // Validate the AppUserFieldMap in the database
        List<AppUserFieldMap> appUserFieldMapList = appUserFieldMapRepository.findAll();
        assertThat(appUserFieldMapList).hasSize(databaseSizeBeforeUpdate);
        AppUserFieldMap testAppUserFieldMap = appUserFieldMapList.get(appUserFieldMapList.size() - 1);
        assertThat(testAppUserFieldMap.getAppId()).isEqualTo(UPDATED_APP_ID);
        assertThat(testAppUserFieldMap.getGroupName()).isEqualTo(UPDATED_GROUP_NAME);
        assertThat(testAppUserFieldMap.getUserFieldCode()).isEqualTo(UPDATED_USER_FIELD_CODE);
        assertThat(testAppUserFieldMap.getUserFieldName()).isEqualTo(UPDATED_USER_FIELD_NAME);
        assertThat(testAppUserFieldMap.getIsMandatory()).isEqualTo(UPDATED_IS_MANDATORY);
        assertThat(testAppUserFieldMap.getProfileScore()).isEqualTo(UPDATED_PROFILE_SCORE);
        assertThat(testAppUserFieldMap.getValidationRegex()).isEqualTo(UPDATED_VALIDATION_REGEX);
        assertThat(testAppUserFieldMap.getRegistrationField()).isEqualTo(UPDATED_REGISTRATION_FIELD);
    }

    @Test
    @Transactional
    public void updateNonExistingAppUserFieldMap() throws Exception {
        int databaseSizeBeforeUpdate = appUserFieldMapRepository.findAll().size();

        // Create the AppUserFieldMap
        AppUserFieldMapDTO appUserFieldMapDTO = appUserFieldMapMapper.toDto(appUserFieldMap);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAppUserFieldMapMockMvc.perform(put("/api/app-user-field-maps").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appUserFieldMapDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AppUserFieldMap in the database
        List<AppUserFieldMap> appUserFieldMapList = appUserFieldMapRepository.findAll();
        assertThat(appUserFieldMapList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAppUserFieldMap() throws Exception {
        // Initialize the database
        appUserFieldMapRepository.saveAndFlush(appUserFieldMap);

        int databaseSizeBeforeDelete = appUserFieldMapRepository.findAll().size();

        // Delete the appUserFieldMap
        restAppUserFieldMapMockMvc.perform(delete("/api/app-user-field-maps/{id}", appUserFieldMap.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AppUserFieldMap> appUserFieldMapList = appUserFieldMapRepository.findAll();
        assertThat(appUserFieldMapList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
