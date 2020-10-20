package com.dreamfolkstech.appconfig.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dreamfolkstech.appconfig.web.rest.TestUtil;

public class MasterUserFieldDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MasterUserFieldDTO.class);
        MasterUserFieldDTO masterUserFieldDTO1 = new MasterUserFieldDTO();
        masterUserFieldDTO1.setId(1L);
        MasterUserFieldDTO masterUserFieldDTO2 = new MasterUserFieldDTO();
        assertThat(masterUserFieldDTO1).isNotEqualTo(masterUserFieldDTO2);
        masterUserFieldDTO2.setId(masterUserFieldDTO1.getId());
        assertThat(masterUserFieldDTO1).isEqualTo(masterUserFieldDTO2);
        masterUserFieldDTO2.setId(2L);
        assertThat(masterUserFieldDTO1).isNotEqualTo(masterUserFieldDTO2);
        masterUserFieldDTO1.setId(null);
        assertThat(masterUserFieldDTO1).isNotEqualTo(masterUserFieldDTO2);
    }
}
