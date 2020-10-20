package com.dreamfolkstech.appconfig.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppConfigMapperTest {

    private AppConfigMapper appConfigMapper;

    @BeforeEach
    public void setUp() {
        appConfigMapper = new AppConfigMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(appConfigMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(appConfigMapper.fromId(null)).isNull();
    }
}
