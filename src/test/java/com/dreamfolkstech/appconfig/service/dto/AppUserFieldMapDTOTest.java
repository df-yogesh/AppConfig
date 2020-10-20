package com.dreamfolkstech.appconfig.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dreamfolkstech.appconfig.web.rest.TestUtil;

public class AppUserFieldMapDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppUserFieldMapDTO.class);
        AppUserFieldMapDTO appUserFieldMapDTO1 = new AppUserFieldMapDTO();
        appUserFieldMapDTO1.setId(1L);
        AppUserFieldMapDTO appUserFieldMapDTO2 = new AppUserFieldMapDTO();
        assertThat(appUserFieldMapDTO1).isNotEqualTo(appUserFieldMapDTO2);
        appUserFieldMapDTO2.setId(appUserFieldMapDTO1.getId());
        assertThat(appUserFieldMapDTO1).isEqualTo(appUserFieldMapDTO2);
        appUserFieldMapDTO2.setId(2L);
        assertThat(appUserFieldMapDTO1).isNotEqualTo(appUserFieldMapDTO2);
        appUserFieldMapDTO1.setId(null);
        assertThat(appUserFieldMapDTO1).isNotEqualTo(appUserFieldMapDTO2);
    }
}
