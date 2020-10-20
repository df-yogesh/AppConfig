package com.dreamfolkstech.appconfig.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dreamfolkstech.appconfig.web.rest.TestUtil;

public class AppConfigTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppConfig.class);
        AppConfig appConfig1 = new AppConfig();
        appConfig1.setId(1L);
        AppConfig appConfig2 = new AppConfig();
        appConfig2.setId(appConfig1.getId());
        assertThat(appConfig1).isEqualTo(appConfig2);
        appConfig2.setId(2L);
        assertThat(appConfig1).isNotEqualTo(appConfig2);
        appConfig1.setId(null);
        assertThat(appConfig1).isNotEqualTo(appConfig2);
    }
}
