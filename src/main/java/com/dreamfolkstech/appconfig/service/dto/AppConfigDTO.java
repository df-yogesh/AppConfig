package com.dreamfolkstech.appconfig.service.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
import com.dreamfolkstech.common.dto.AbstractAuditingDTO;

/**
 * A DTO for the {@link com.dreamfolkstech.appconfig.domain.AppConfig} entity.
 */
public class AppConfigDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @NotNull
    @Size(min = 1, max = 20)
    private String code;

    private String value;

    private GenericStatus status;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public GenericStatus getStatus() {
        return status;
    }

    public void setStatus(GenericStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppConfigDTO)) {
            return false;
        }

        return id != null && id.equals(((AppConfigDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppConfigDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", value='" + getValue() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
