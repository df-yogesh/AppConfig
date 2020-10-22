package com.dreamfolkstech.appconfig.service.dto;

import java.io.Serializable;

import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
import com.dreamfolkstech.common.dto.AbstractAuditingDTO;

/**
 * A DTO for the {@link com.dreamfolkstech.appconfig.domain.AppProductOfferingService} entity.
 */
public class AppProductOfferingServiceDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private Integer productServiceId;

    private GenericStatus status;


    private Long applicationId;

    private String applicationName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductServiceId() {
        return productServiceId;
    }

    public void setProductServiceId(Integer productServiceId) {
        this.productServiceId = productServiceId;
    }

    public GenericStatus getStatus() {
        return status;
    }

    public void setStatus(GenericStatus status) {
        this.status = status;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppProductOfferingServiceDTO)) {
            return false;
        }

        return id != null && id.equals(((AppProductOfferingServiceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppProductServiceDTO{" +
            "id=" + getId() +
            ", productServiceId=" + getProductServiceId() +
            ", status='" + getStatus() + "'" +
            ", applicationId=" + getApplicationId() +
            ", applicationName='" + getApplicationName() + "'" +
            "}";
    }
}
