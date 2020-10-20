package com.dreamfolkstech.appconfig.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dreamfolkstech.appconfig.web.rest.TestUtil;

public class MasterUserFieldTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MasterUserField.class);
        MasterUserField masterUserField1 = new MasterUserField();
        masterUserField1.setId(1L);
        MasterUserField masterUserField2 = new MasterUserField();
        masterUserField2.setId(masterUserField1.getId());
        assertThat(masterUserField1).isEqualTo(masterUserField2);
        masterUserField2.setId(2L);
        assertThat(masterUserField1).isNotEqualTo(masterUserField2);
        masterUserField1.setId(null);
        assertThat(masterUserField1).isNotEqualTo(masterUserField2);
    }
}
