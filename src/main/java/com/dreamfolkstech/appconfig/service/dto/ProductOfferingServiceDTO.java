package com.dreamfolkstech.appconfig.service.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
import com.dreamfolkstech.common.dto.AbstractAuditingDTO;

/**
 * A DTO for the {@link com.dreamfolkstech.appconfig.domain.ProductOfferingService} entity.
 */
public class ProductOfferingServiceDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @NotNull
    @Size(min = 1, max = 20)
    private String code;

    @Size(min = 1, max = 200)
    private String description;

    private GenericStatus status;

    private Long productOfferingId;

    private String productOfferingName;
    
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GenericStatus getStatus() {
        return status;
    }

    public void setStatus(GenericStatus status) {
        this.status = status;
    }

    public Long getProductOfferingId() {
        return productOfferingId;
    }

    public void setProductOfferingId(Long productOfferingId) {
        this.productOfferingId = productOfferingId;
    }

    public String getProductOfferingName() {
        return productOfferingName;
    }

    public void setProductOfferingName(String productOfferingName) {
        this.productOfferingName = productOfferingName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductOfferingServiceDTO)) {
            return false;
        }

        return id != null && id.equals(((ProductOfferingServiceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductServiceDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", productOfferingId=" + getProductOfferingId() +
            ", productOfferingName='" + getProductOfferingName() + "'" +
            "}";
    }
}
