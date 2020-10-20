package com.dreamfolkstech.appconfig.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductServiceMapperTest {

    private ProductServiceMapper productServiceMapper;

    @BeforeEach
    public void setUp() {
        productServiceMapper = new ProductServiceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(productServiceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(productServiceMapper.fromId(null)).isNull();
    }
}
