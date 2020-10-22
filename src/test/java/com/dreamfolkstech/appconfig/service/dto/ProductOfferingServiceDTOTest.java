package com.dreamfolkstech.appconfig.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dreamfolkstech.appconfig.web.rest.TestUtil;

public class ProductOfferingServiceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductOfferingServiceDTO.class);
        ProductOfferingServiceDTO productServiceDTO1 = new ProductOfferingServiceDTO();
        productServiceDTO1.setId(1L);
        ProductOfferingServiceDTO productServiceDTO2 = new ProductOfferingServiceDTO();
        assertThat(productServiceDTO1).isNotEqualTo(productServiceDTO2);
        productServiceDTO2.setId(productServiceDTO1.getId());
        assertThat(productServiceDTO1).isEqualTo(productServiceDTO2);
        productServiceDTO2.setId(2L);
        assertThat(productServiceDTO1).isNotEqualTo(productServiceDTO2);
        productServiceDTO1.setId(null);
        assertThat(productServiceDTO1).isNotEqualTo(productServiceDTO2);
    }
}
