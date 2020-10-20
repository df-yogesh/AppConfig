package com.dreamfolkstech.appconfig.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dreamfolkstech.appconfig.web.rest.TestUtil;

public class AppProductServiceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppProductServiceDTO.class);
        AppProductServiceDTO appProductServiceDTO1 = new AppProductServiceDTO();
        appProductServiceDTO1.setId(1L);
        AppProductServiceDTO appProductServiceDTO2 = new AppProductServiceDTO();
        assertThat(appProductServiceDTO1).isNotEqualTo(appProductServiceDTO2);
        appProductServiceDTO2.setId(appProductServiceDTO1.getId());
        assertThat(appProductServiceDTO1).isEqualTo(appProductServiceDTO2);
        appProductServiceDTO2.setId(2L);
        assertThat(appProductServiceDTO1).isNotEqualTo(appProductServiceDTO2);
        appProductServiceDTO1.setId(null);
        assertThat(appProductServiceDTO1).isNotEqualTo(appProductServiceDTO2);
    }
}
