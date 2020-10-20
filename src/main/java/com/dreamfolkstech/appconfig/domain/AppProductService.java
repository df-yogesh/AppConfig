package com.dreamfolkstech.appconfig.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dreamfolkstech.common.domain.AbstractBaseExtensibleEntity;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A AppProductService.
 */
@Entity
@Table(name = "app_product_service")
public class AppProductService extends AbstractBaseExtensibleEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "product_service_id")
    private Integer productServiceId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private GenericStatus status;

    @ManyToOne
    @JsonIgnoreProperties(value = "appProductServices", allowSetters = true)
    private Application application;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getProductServiceId() {
        return productServiceId;
    }

    public AppProductService productServiceId(Integer productServiceId) {
        this.productServiceId = productServiceId;
        return this;
    }

    public void setProductServiceId(Integer productServiceId) {
        this.productServiceId = productServiceId;
    }

    public GenericStatus getStatus() {
        return status;
    }

    public AppProductService status(GenericStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(GenericStatus status) {
        this.status = status;
    }

    public Application getApplication() {
        return application;
    }

    public AppProductService application(Application application) {
        this.application = application;
        return this;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppProductService)) {
            return false;
        }
        return this.getId() != null && this.getId().equals(((AppProductService) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppProductService{" +
            "id=" + getId() +
            ", productServiceId=" + getProductServiceId() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
