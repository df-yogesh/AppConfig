package com.dreamfolkstech.appconfig.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppUserFieldMapMapperTest {

    private AppUserFieldMapMapper appUserFieldMapMapper;

    @BeforeEach
    public void setUp() {
        appUserFieldMapMapper = new AppUserFieldMapMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(appUserFieldMapMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(appUserFieldMapMapper.fromId(null)).isNull();
    }
}
