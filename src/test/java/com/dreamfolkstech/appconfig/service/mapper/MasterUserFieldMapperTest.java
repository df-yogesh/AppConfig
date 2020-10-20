package com.dreamfolkstech.appconfig.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MasterUserFieldMapperTest {

    private MasterUserFieldMapper masterUserFieldMapper;

    @BeforeEach
    public void setUp() {
        masterUserFieldMapper = new MasterUserFieldMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(masterUserFieldMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(masterUserFieldMapper.fromId(null)).isNull();
    }
}
