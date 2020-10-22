package com.dreamfolkstech.appconfig.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dreamfolkstech.appconfig.web.rest.TestUtil;

public class AppProductOfferingServiceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppProductOfferingService.class);
        AppProductOfferingService appProductService1 = new AppProductOfferingService();
        appProductService1.setId(1L);
        AppProductOfferingService appProductService2 = new AppProductOfferingService();
        appProductService2.setId(appProductService1.getId());
        assertThat(appProductService1).isEqualTo(appProductService2);
        appProductService2.setId(2L);
        assertThat(appProductService1).isNotEqualTo(appProductService2);
        appProductService1.setId(null);
        assertThat(appProductService1).isNotEqualTo(appProductService2);
    }
}
