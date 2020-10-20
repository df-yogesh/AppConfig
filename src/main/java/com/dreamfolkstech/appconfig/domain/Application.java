package com.dreamfolkstech.appconfig.domain;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dreamfolkstech.common.domain.AbstractBaseExtensibleEntity;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;

/**
 * A Application.
 */
@Entity
@Table(name = "application")
public class Application extends AbstractBaseExtensibleEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "code", length = 20, nullable = false)
    private String code;

    @Size(min = 1, max = 200)
    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "partner_id")
    private Integer partnerId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private GenericStatus status;

    @OneToMany(mappedBy = "application")
    private Set<AppProductService> appProductServices = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getName() {
        return name;
    }

    public Application name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public Application code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public Application description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public Application partnerId(Integer partnerId) {
        this.partnerId = partnerId;
        return this;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public GenericStatus getStatus() {
        return status;
    }

    public Application status(GenericStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(GenericStatus status) {
        this.status = status;
    }

    public Set<AppProductService> getAppProductServices() {
        return appProductServices;
    }

    public Application appProductServices(Set<AppProductService> appProductServices) {
        this.appProductServices = appProductServices;
        return this;
    }

    public Application addAppProductService(AppProductService appProductService) {
        this.appProductServices.add(appProductService);
        appProductService.setApplication(this);
        return this;
    }

    public Application removeAppProductService(AppProductService appProductService) {
        this.appProductServices.remove(appProductService);
        appProductService.setApplication(null);
        return this;
    }

    public void setAppProductServices(Set<AppProductService> appProductServices) {
        this.appProductServices = appProductServices;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Application)) {
            return false;
        }
        return this.getId() != null && this.getId().equals(((Application) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Application{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", partnerId=" + getPartnerId() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
