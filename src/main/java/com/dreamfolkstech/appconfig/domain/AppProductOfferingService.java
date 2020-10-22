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
@Table(name = "app_product_offering_service")
public class AppProductOfferingService extends AbstractBaseExtensibleEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "product_offering_service_id")
    private Integer productOfferingServiceId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private GenericStatus status;

    @ManyToOne
    @JsonIgnoreProperties(value = "appProductOfferingServices", allowSetters = true)
    private Application application;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getProductOfferingServiceId() {
        return productOfferingServiceId;
    }

    public AppProductOfferingService productOfferingServiceId(Integer productOfferingServiceId) {
        this.productOfferingServiceId = productOfferingServiceId;
        return this;
    }

    public void setProductOfferingServiceId(Integer productOfferingServiceId) {
        this.productOfferingServiceId = productOfferingServiceId;
    }

    public GenericStatus getStatus() {
        return status;
    }

    public AppProductOfferingService status(GenericStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(GenericStatus status) {
        this.status = status;
    }

    public Application getApplication() {
        return application;
    }

    public AppProductOfferingService application(Application application) {
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
        if (!(o instanceof AppProductOfferingService)) {
            return false;
        }
        return this.getId() != null && this.getId().equals(((AppProductOfferingService) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppProductOfferingService{" +
            "id=" + getId() +
            ", productServiceId=" + getProductOfferingServiceId() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
