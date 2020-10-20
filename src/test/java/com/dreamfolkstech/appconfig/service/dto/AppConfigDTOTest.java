package com.dreamfolkstech.appconfig.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dreamfolkstech.appconfig.web.rest.TestUtil;

public class AppConfigDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppConfigDTO.class);
        AppConfigDTO appConfigDTO1 = new AppConfigDTO();
        appConfigDTO1.setId(1L);
        AppConfigDTO appConfigDTO2 = new AppConfigDTO();
        assertThat(appConfigDTO1).isNotEqualTo(appConfigDTO2);
        appConfigDTO2.setId(appConfigDTO1.getId());
        assertThat(appConfigDTO1).isEqualTo(appConfigDTO2);
        appConfigDTO2.setId(2L);
        assertThat(appConfigDTO1).isNotEqualTo(appConfigDTO2);
        appConfigDTO1.setId(null);
        assertThat(appConfigDTO1).isNotEqualTo(appConfigDTO2);
    }
}
