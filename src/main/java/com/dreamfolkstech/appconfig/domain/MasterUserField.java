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
 * A MasterUserField.
 */
@Entity
@Table(name = "master_user_field")
public class MasterUserField extends AbstractBaseExtensibleEntity<Long> implements Serializable {

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

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private GenericStatus status;

    @OneToMany(mappedBy = "masterUserField")
    private Set<AppUserFieldMap> appUserFieldMaps = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getName() {
        return name;
    }

    public MasterUserField name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public MasterUserField code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public MasterUserField description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GenericStatus getStatus() {
        return status;
    }

    public MasterUserField status(GenericStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(GenericStatus status) {
        this.status = status;
    }

    public Set<AppUserFieldMap> getAppUserFieldMaps() {
        return appUserFieldMaps;
    }

    public MasterUserField appUserFieldMaps(Set<AppUserFieldMap> appUserFieldMaps) {
        this.appUserFieldMaps = appUserFieldMaps;
        return this;
    }

    public MasterUserField addAppUserFieldMap(AppUserFieldMap appUserFieldMap) {
        this.appUserFieldMaps.add(appUserFieldMap);
        appUserFieldMap.setMasterUserField(this);
        return this;
    }

    public MasterUserField removeAppUserFieldMap(AppUserFieldMap appUserFieldMap) {
        this.appUserFieldMaps.remove(appUserFieldMap);
        appUserFieldMap.setMasterUserField(null);
        return this;
    }

    public void setAppUserFieldMaps(Set<AppUserFieldMap> appUserFieldMaps) {
        this.appUserFieldMaps = appUserFieldMaps;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MasterUserField)) {
            return false;
        }
        return this.getId() != null && this.getId().equals(((MasterUserField) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MasterUserField{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
