package com.dreamfolkstech.appconfig.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dreamfolkstech.appconfig.web.rest.TestUtil;

public class ProductServiceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductServiceDTO.class);
        ProductServiceDTO productServiceDTO1 = new ProductServiceDTO();
        productServiceDTO1.setId(1L);
        ProductServiceDTO productServiceDTO2 = new ProductServiceDTO();
        assertThat(productServiceDTO1).isNotEqualTo(productServiceDTO2);
        productServiceDTO2.setId(productServiceDTO1.getId());
        assertThat(productServiceDTO1).isEqualTo(productServiceDTO2);
        productServiceDTO2.setId(2L);
        assertThat(productServiceDTO1).isNotEqualTo(productServiceDTO2);
        productServiceDTO1.setId(null);
        assertThat(productServiceDTO1).isNotEqualTo(productServiceDTO2);
    }
}
