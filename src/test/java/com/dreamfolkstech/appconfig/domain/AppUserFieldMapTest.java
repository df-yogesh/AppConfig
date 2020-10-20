package com.dreamfolkstech.appconfig.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dreamfolkstech.appconfig.web.rest.TestUtil;

public class AppUserFieldMapTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppUserFieldMap.class);
        AppUserFieldMap appUserFieldMap1 = new AppUserFieldMap();
        appUserFieldMap1.setId(1L);
        AppUserFieldMap appUserFieldMap2 = new AppUserFieldMap();
        appUserFieldMap2.setId(appUserFieldMap1.getId());
        assertThat(appUserFieldMap1).isEqualTo(appUserFieldMap2);
        appUserFieldMap2.setId(2L);
        assertThat(appUserFieldMap1).isNotEqualTo(appUserFieldMap2);
        appUserFieldMap1.setId(null);
        assertThat(appUserFieldMap1).isNotEqualTo(appUserFieldMap2);
    }
}
