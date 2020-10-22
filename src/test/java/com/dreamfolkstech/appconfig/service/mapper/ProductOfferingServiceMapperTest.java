package com.dreamfolkstech.appconfig.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductOfferingServiceMapperTest {

    private ProductOfferingServiceMapper productOfferingServiceMapper;

    @BeforeEach
    public void setUp() {
        productOfferingServiceMapper = new ProductOfferingServiceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(productOfferingServiceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(productOfferingServiceMapper.fromId(null)).isNull();
    }
}
