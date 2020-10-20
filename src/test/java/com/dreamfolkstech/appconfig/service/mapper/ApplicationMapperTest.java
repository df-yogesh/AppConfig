package com.dreamfolkstech.appconfig.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ApplicationMapperTest {

    private ApplicationMapper applicationMapper;

    @BeforeEach
    public void setUp() {
        applicationMapper = new ApplicationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(applicationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(applicationMapper.fromId(null)).isNull();
    }
}
