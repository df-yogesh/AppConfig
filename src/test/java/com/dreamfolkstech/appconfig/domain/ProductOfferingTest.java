package com.dreamfolkstech.appconfig.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dreamfolkstech.appconfig.web.rest.TestUtil;

public class ProductOfferingTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductOffering.class);
        ProductOffering productOffering1 = new ProductOffering();
        productOffering1.setId(1L);
        ProductOffering productOffering2 = new ProductOffering();
        productOffering2.setId(productOffering1.getId());
        assertThat(productOffering1).isEqualTo(productOffering2);
        productOffering2.setId(2L);
        assertThat(productOffering1).isNotEqualTo(productOffering2);
        productOffering1.setId(null);
        assertThat(productOffering1).isNotEqualTo(productOffering2);
    }
}
