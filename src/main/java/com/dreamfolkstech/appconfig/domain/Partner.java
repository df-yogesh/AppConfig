package com.dreamfolkstech.appconfig.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dreamfolkstech.common.domain.AbstractBaseExtensibleEntity;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;

/**
 * A Partner.
 */
@Entity
@Table(name = "partner")
public class Partner extends AbstractBaseExtensibleEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Size(min = 1, max = 200)
    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "owner_type")
    private Integer ownerType;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private GenericStatus status;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getName() {
        return name;
    }

    public Partner name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Partner description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOwnerType() {
        return ownerType;
    }

    public Partner ownerType(Integer ownerType) {
        this.ownerType = ownerType;
        return this;
    }

    public void setOwnerType(Integer ownerType) {
        this.ownerType = ownerType;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public Partner sortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public GenericStatus getStatus() {
        return status;
    }

    public Partner status(GenericStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(GenericStatus status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Partner)) {
            return false;
        }
        return this.getId() != null && this.getId().equals(((Partner) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Partner{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", ownerType=" + getOwnerType() +
            ", sortOrder=" + getSortOrder() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
