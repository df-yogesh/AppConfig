package com.dreamfolkstech.appconfig.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dreamfolkstech.appconfig.web.rest.TestUtil;

public class ProductServiceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductService.class);
        ProductService productService1 = new ProductService();
        productService1.setId(1L);
        ProductService productService2 = new ProductService();
        productService2.setId(productService1.getId());
        assertThat(productService1).isEqualTo(productService2);
        productService2.setId(2L);
        assertThat(productService1).isNotEqualTo(productService2);
        productService1.setId(null);
        assertThat(productService1).isNotEqualTo(productService2);
    }
}
