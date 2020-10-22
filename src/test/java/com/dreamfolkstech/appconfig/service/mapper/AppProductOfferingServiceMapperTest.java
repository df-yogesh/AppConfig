package com.dreamfolkstech.appconfig.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppProductOfferingServiceMapperTest {

    private AppProductOfferingServiceMapper appProductOfferingServiceMapper;

    @BeforeEach
    public void setUp() {
        appProductOfferingServiceMapper = new AppProductOfferingServiceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(appProductOfferingServiceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(appProductOfferingServiceMapper.fromId(null)).isNull();
    }
}
