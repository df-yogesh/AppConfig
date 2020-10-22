package com.dreamfolkstech.appconfig.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dreamfolkstech.appconfig.web.rest.TestUtil;

public class ProductOfferingServiceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductOfferingService.class);
        ProductOfferingService productService1 = new ProductOfferingService();
        productService1.setId(1L);
        ProductOfferingService productService2 = new ProductOfferingService();
        productService2.setId(productService1.getId());
        assertThat(productService1).isEqualTo(productService2);
        productService2.setId(2L);
        assertThat(productService1).isNotEqualTo(productService2);
        productService1.setId(null);
        assertThat(productService1).isNotEqualTo(productService2);
    }
}
