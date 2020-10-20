package com.dreamfolkstech.appconfig.service.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
import com.dreamfolkstech.common.dto.AbstractAuditingDTO;

/**
 * A DTO for the {@link com.dreamfolkstech.appconfig.domain.Partner} entity.
 */
public class PartnerDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @Size(min = 1, max = 200)
    private String description;

    private Integer ownerType;

    private Integer sortOrder;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(Integer ownerType) {
        this.ownerType = ownerType;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
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
        if (!(o instanceof PartnerDTO)) {
            return false;
        }

        return id != null && id.equals(((PartnerDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PartnerDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", ownerType=" + getOwnerType() +
            ", sortOrder=" + getSortOrder() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
