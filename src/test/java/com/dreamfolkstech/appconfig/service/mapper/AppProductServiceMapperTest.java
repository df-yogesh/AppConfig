package com.dreamfolkstech.appconfig.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppProductServiceMapperTest {

    private AppProductServiceMapper appProductServiceMapper;

    @BeforeEach
    public void setUp() {
        appProductServiceMapper = new AppProductServiceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(appProductServiceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(appProductServiceMapper.fromId(null)).isNull();
    }
}
