package com.dreamfolkstech.appconfig.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dreamfolkstech.appconfig.web.rest.TestUtil;

public class ProductOfferingDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductOfferingDTO.class);
        ProductOfferingDTO productOfferingDTO1 = new ProductOfferingDTO();
        productOfferingDTO1.setId(1L);
        ProductOfferingDTO productOfferingDTO2 = new ProductOfferingDTO();
        assertThat(productOfferingDTO1).isNotEqualTo(productOfferingDTO2);
        productOfferingDTO2.setId(productOfferingDTO1.getId());
        assertThat(productOfferingDTO1).isEqualTo(productOfferingDTO2);
        productOfferingDTO2.setId(2L);
        assertThat(productOfferingDTO1).isNotEqualTo(productOfferingDTO2);
        productOfferingDTO1.setId(null);
        assertThat(productOfferingDTO1).isNotEqualTo(productOfferingDTO2);
    }
}
