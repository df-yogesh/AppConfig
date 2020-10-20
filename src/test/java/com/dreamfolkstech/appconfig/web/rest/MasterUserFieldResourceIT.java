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
import com.dreamfolkstech.appconfig.domain.MasterUserField;
import com.dreamfolkstech.appconfig.repository.MasterUserFieldRepository;
import com.dreamfolkstech.appconfig.service.MasterUserFieldService;
import com.dreamfolkstech.appconfig.service.dto.MasterUserFieldDTO;
import com.dreamfolkstech.appconfig.service.mapper.MasterUserFieldMapper;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
/**
 * Integration tests for the {@link MasterUserFieldResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, AppConfigApp.class })
@AutoConfigureMockMvc
@WithMockUser
public class MasterUserFieldResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final GenericStatus DEFAULT_STATUS = GenericStatus.ENABLED;
    private static final GenericStatus UPDATED_STATUS = GenericStatus.DISABLED;

    @Autowired
    private MasterUserFieldRepository masterUserFieldRepository;

    @Autowired
    private MasterUserFieldMapper masterUserFieldMapper;

    @Autowired
    private MasterUserFieldService masterUserFieldService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMasterUserFieldMockMvc;

    private MasterUserField masterUserField;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MasterUserField createEntity(EntityManager em) {
        MasterUserField masterUserField = new MasterUserField()
            .name(DEFAULT_NAME)
            .code(DEFAULT_CODE)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS);
        return masterUserField;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MasterUserField createUpdatedEntity(EntityManager em) {
        MasterUserField masterUserField = new MasterUserField()
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS);
        return masterUserField;
    }

    @BeforeEach
    public void initTest() {
        masterUserField = createEntity(em);
    }

    @Test
    @Transactional
    public void createMasterUserField() throws Exception {
        int databaseSizeBeforeCreate = masterUserFieldRepository.findAll().size();
        // Create the MasterUserField
        MasterUserFieldDTO masterUserFieldDTO = masterUserFieldMapper.toDto(masterUserField);
        restMasterUserFieldMockMvc.perform(post("/api/master-user-fields").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(masterUserFieldDTO)))
            .andExpect(status().isCreated());

        // Validate the MasterUserField in the database
        List<MasterUserField> masterUserFieldList = masterUserFieldRepository.findAll();
        assertThat(masterUserFieldList).hasSize(databaseSizeBeforeCreate + 1);
        MasterUserField testMasterUserField = masterUserFieldList.get(masterUserFieldList.size() - 1);
        assertThat(testMasterUserField.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMasterUserField.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testMasterUserField.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMasterUserField.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createMasterUserFieldWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = masterUserFieldRepository.findAll().size();

        // Create the MasterUserField with an existing ID
        masterUserField.setId(1L);
        MasterUserFieldDTO masterUserFieldDTO = masterUserFieldMapper.toDto(masterUserField);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMasterUserFieldMockMvc.perform(post("/api/master-user-fields").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(masterUserFieldDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MasterUserField in the database
        List<MasterUserField> masterUserFieldList = masterUserFieldRepository.findAll();
        assertThat(masterUserFieldList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = masterUserFieldRepository.findAll().size();
        // set the field null
        masterUserField.setName(null);

        // Create the MasterUserField, which fails.
        MasterUserFieldDTO masterUserFieldDTO = masterUserFieldMapper.toDto(masterUserField);


        restMasterUserFieldMockMvc.perform(post("/api/master-user-fields").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(masterUserFieldDTO)))
            .andExpect(status().isBadRequest());

        List<MasterUserField> masterUserFieldList = masterUserFieldRepository.findAll();
        assertThat(masterUserFieldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = masterUserFieldRepository.findAll().size();
        // set the field null
        masterUserField.setCode(null);

        // Create the MasterUserField, which fails.
        MasterUserFieldDTO masterUserFieldDTO = masterUserFieldMapper.toDto(masterUserField);


        restMasterUserFieldMockMvc.perform(post("/api/master-user-fields").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(masterUserFieldDTO)))
            .andExpect(status().isBadRequest());

        List<MasterUserField> masterUserFieldList = masterUserFieldRepository.findAll();
        assertThat(masterUserFieldList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMasterUserFields() throws Exception {
        // Initialize the database
        masterUserFieldRepository.saveAndFlush(masterUserField);

        // Get all the masterUserFieldList
        restMasterUserFieldMockMvc.perform(get("/api/master-user-fields?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(masterUserField.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getMasterUserField() throws Exception {
        // Initialize the database
        masterUserFieldRepository.saveAndFlush(masterUserField);

        // Get the masterUserField
        restMasterUserFieldMockMvc.perform(get("/api/master-user-fields/{id}", masterUserField.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(masterUserField.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingMasterUserField() throws Exception {
        // Get the masterUserField
        restMasterUserFieldMockMvc.perform(get("/api/master-user-fields/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMasterUserField() throws Exception {
        // Initialize the database
        masterUserFieldRepository.saveAndFlush(masterUserField);

        int databaseSizeBeforeUpdate = masterUserFieldRepository.findAll().size();

        // Update the masterUserField
        MasterUserField updatedMasterUserField = masterUserFieldRepository.findById(masterUserField.getId()).get();
        // Disconnect from session so that the updates on updatedMasterUserField are not directly saved in db
        em.detach(updatedMasterUserField);
        updatedMasterUserField
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS);
        MasterUserFieldDTO masterUserFieldDTO = masterUserFieldMapper.toDto(updatedMasterUserField);

        restMasterUserFieldMockMvc.perform(put("/api/master-user-fields").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(masterUserFieldDTO)))
            .andExpect(status().isOk());

        // Validate the MasterUserField in the database
        List<MasterUserField> masterUserFieldList = masterUserFieldRepository.findAll();
        assertThat(masterUserFieldList).hasSize(databaseSizeBeforeUpdate);
        MasterUserField testMasterUserField = masterUserFieldList.get(masterUserFieldList.size() - 1);
        assertThat(testMasterUserField.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMasterUserField.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testMasterUserField.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMasterUserField.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingMasterUserField() throws Exception {
        int databaseSizeBeforeUpdate = masterUserFieldRepository.findAll().size();

        // Create the MasterUserField
        MasterUserFieldDTO masterUserFieldDTO = masterUserFieldMapper.toDto(masterUserField);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMasterUserFieldMockMvc.perform(put("/api/master-user-fields").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(masterUserFieldDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MasterUserField in the database
        List<MasterUserField> masterUserFieldList = masterUserFieldRepository.findAll();
        assertThat(masterUserFieldList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMasterUserField() throws Exception {
        // Initialize the database
        masterUserFieldRepository.saveAndFlush(masterUserField);

        int databaseSizeBeforeDelete = masterUserFieldRepository.findAll().size();

        // Delete the masterUserField
        restMasterUserFieldMockMvc.perform(delete("/api/master-user-fields/{id}", masterUserField.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MasterUserField> masterUserFieldList = masterUserFieldRepository.findAll();
        assertThat(masterUserFieldList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
