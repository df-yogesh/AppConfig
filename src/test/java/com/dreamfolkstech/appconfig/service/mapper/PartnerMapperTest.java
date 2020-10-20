package com.dreamfolkstech.appconfig.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PartnerMapperTest {

    private PartnerMapper partnerMapper;

    @BeforeEach
    public void setUp() {
        partnerMapper = new PartnerMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(partnerMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(partnerMapper.fromId(null)).isNull();
    }
}
