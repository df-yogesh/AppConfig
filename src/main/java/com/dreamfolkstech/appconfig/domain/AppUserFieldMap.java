package com.dreamfolkstech.appconfig.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.dreamfolkstech.common.domain.AbstractBaseExtensibleEntity;
import com.dreamfolkstech.common.domain.enumeration.BooleanValue;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A AppUserFieldMap.
 */
@Entity
@Table(name = "app_user_field_map")
public class AppUserFieldMap extends AbstractBaseExtensibleEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "app_id")
    private Integer appId;

    @Size(min = 1, max = 50)
    @Column(name = "group_name", length = 50)
    private String groupName;

    @Size(min = 1, max = 20)
    @Column(name = "user_field_code", length = 20)
    private String userFieldCode;

    @Size(min = 1, max = 50)
    @Column(name = "user_field_name", length = 50)
    private String userFieldName;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "is_mandatory")
    private BooleanValue isMandatory;

    @Column(name = "profile_score")
    private Integer profileScore;

    @Column(name = "validation_regex")
    private String validationRegex;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "registration_field")
    private BooleanValue registrationField;

    @ManyToOne
    @JsonIgnoreProperties(value = "appUserFieldMaps", allowSetters = true)
    private MasterUserField masterUserField;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private GenericStatus status;
    
    @Column(name = "group_sort_order")
    private Integer groupSortOrder;
    
    @Column(name = "field_sort_order")
    private Integer fieldSortOrder;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Integer getAppId() {
        return appId;
    }

    public AppUserFieldMap appId(Integer appId) {
        this.appId = appId;
        return this;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getGroupName() {
        return groupName;
    }

    public AppUserFieldMap groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getUserFieldCode() {
        return userFieldCode;
    }

    public AppUserFieldMap userFieldCode(String userFieldCode) {
        this.userFieldCode = userFieldCode;
        return this;
    }

    public void setUserFieldCode(String userFieldCode) {
        this.userFieldCode = userFieldCode;
    }

    public String getUserFieldName() {
        return userFieldName;
    }

    public AppUserFieldMap userFieldName(String userFieldName) {
        this.userFieldName = userFieldName;
        return this;
    }

    public void setUserFieldName(String userFieldName) {
        this.userFieldName = userFieldName;
    }

    public BooleanValue getIsMandatory() {
        return isMandatory;
    }

    public AppUserFieldMap isMandatory(BooleanValue isMandatory) {
        this.isMandatory = isMandatory;
        return this;
    }

    public void setIsMandatory(BooleanValue isMandatory) {
        this.isMandatory = isMandatory;
    }

    public Integer getProfileScore() {
        return profileScore;
    }

    public AppUserFieldMap profileScore(Integer profileScore) {
        this.profileScore = profileScore;
        return this;
    }

    public void setProfileScore(Integer profileScore) {
        this.profileScore = profileScore;
    }

    public String getValidationRegex() {
        return validationRegex;
    }

    public AppUserFieldMap validationRegex(String validationRegex) {
        this.validationRegex = validationRegex;
        return this;
    }

    public void setValidationRegex(String validationRegex) {
        this.validationRegex = validationRegex;
    }

    public BooleanValue getRegistrationField() {
        return registrationField;
    }

    public AppUserFieldMap registrationField(BooleanValue registrationField) {
        this.registrationField = registrationField;
        return this;
    }

    public void setRegistrationField(BooleanValue registrationField) {
        this.registrationField = registrationField;
    }

    public MasterUserField getMasterUserField() {
        return masterUserField;
    }

    public AppUserFieldMap masterUserField(MasterUserField masterUserField) {
        this.masterUserField = masterUserField;
        return this;
    }

    public void setMasterUserField(MasterUserField masterUserField) {
        this.masterUserField = masterUserField;
    }

    
    public GenericStatus getStatus() {
		return status;
	}

	public void setStatus(GenericStatus status) {
		this.status = status;
	}

	
	public Integer getGroupSortOrder() {
		return groupSortOrder;
	}

	public void setGroupSortOrder(Integer groupSortOrder) {
		this.groupSortOrder = groupSortOrder;
	}

	public Integer getFieldSortOrder() {
		return fieldSortOrder;
	}

	public void setFieldSortOrder(Integer fieldSortOrder) {
		this.fieldSortOrder = fieldSortOrder;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppUserFieldMap)) {
            return false;
        }
        return this.getId() != null && this.getId().equals(((AppUserFieldMap) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppUserFieldMap{" +
            "id=" + getId() +
            ", appId=" + getAppId() +
            ", groupName='" + getGroupName() + "'" +
            ", userFieldCode='" + getUserFieldCode() + "'" +
            ", userFieldName='" + getUserFieldName() + "'" +
            ", isMandatory='" + getIsMandatory() + "'" +
            ", profileScore=" + getProfileScore() +
            ", validationRegex='" + getValidationRegex() + "'" +
            ", registrationField='" + getRegistrationField() + "'" +
            "}";
    }
}
