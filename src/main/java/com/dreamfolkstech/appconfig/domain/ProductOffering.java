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
 * A ProductOffering.
 */
@Entity
@Table(name = "product_offering")
public class ProductOffering extends AbstractBaseExtensibleEntity<Long> implements Serializable {

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

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private GenericStatus status;

    @OneToMany(mappedBy = "productOffering")
    private Set<ProductService> productServices = new HashSet<>();


    public String getName() {
        return name;
    }

    public ProductOffering name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public ProductOffering code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public ProductOffering description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public ProductOffering sortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public GenericStatus getStatus() {
        return status;
    }

    public ProductOffering status(GenericStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(GenericStatus status) {
        this.status = status;
    }

    public Set<ProductService> getProductServices() {
        return productServices;
    }

    public ProductOffering productServices(Set<ProductService> productServices) {
        this.productServices = productServices;
        return this;
    }

    public ProductOffering addProductService(ProductService productService) {
        this.productServices.add(productService);
        productService.setProductOffering(this);
        return this;
    }

    public ProductOffering removeProductService(ProductService productService) {
        this.productServices.remove(productService);
        productService.setProductOffering(null);
        return this;
    }

    public void setProductServices(Set<ProductService> productServices) {
        this.productServices = productServices;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductOffering)) {
            return false;
        }
        return this.getId() != null && this.getId().equals(((ProductOffering) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductOffering{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", sortOrder=" + getSortOrder() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
