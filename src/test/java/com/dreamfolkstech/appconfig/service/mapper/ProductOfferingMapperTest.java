package com.dreamfolkstech.appconfig.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductOfferingMapperTest {

    private ProductOfferingMapper productOfferingMapper;

    @BeforeEach
    public void setUp() {
        productOfferingMapper = new ProductOfferingMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(productOfferingMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(productOfferingMapper.fromId(null)).isNull();
    }
}
