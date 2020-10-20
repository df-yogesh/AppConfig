package com.dreamfolkstech.appconfig.service.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import com.dreamfolkstech.common.domain.enumeration.BooleanValue;
import com.dreamfolkstech.common.domain.enumeration.GenericStatus;
import com.dreamfolkstech.common.dto.AbstractAuditingDTO;

/**
 * A DTO for the {@link com.dreamfolkstech.appconfig.domain.AppUserFieldMap} entity.
 */
public class AppUserFieldMapDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private Integer appId;

    @Size(min = 1, max = 50)
    private String groupName;

    @Size(min = 1, max = 20)
    private String userFieldCode;

    @Size(min = 1, max = 50)
    private String userFieldName;

    private BooleanValue isMandatory;

    private Integer profileScore;

    private String validationRegex;

    private BooleanValue registrationField;

    private GenericStatus status;


    private Long masterUserFieldId;

    private String masterUserFieldName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getUserFieldCode() {
        return userFieldCode;
    }

    public void setUserFieldCode(String userFieldCode) {
        this.userFieldCode = userFieldCode;
    }

    public String getUserFieldName() {
        return userFieldName;
    }

    public void setUserFieldName(String userFieldName) {
        this.userFieldName = userFieldName;
    }

    public BooleanValue getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(BooleanValue isMandatory) {
        this.isMandatory = isMandatory;
    }

    public Integer getProfileScore() {
        return profileScore;
    }

    public void setProfileScore(Integer profileScore) {
        this.profileScore = profileScore;
    }

    public String getValidationRegex() {
        return validationRegex;
    }

    public void setValidationRegex(String validationRegex) {
        this.validationRegex = validationRegex;
    }

    public BooleanValue getRegistrationField() {
        return registrationField;
    }

    public void setRegistrationField(BooleanValue registrationField) {
        this.registrationField = registrationField;
    }

    public Long getMasterUserFieldId() {
        return masterUserFieldId;
    }

    public void setMasterUserFieldId(Long masterUserFieldId) {
        this.masterUserFieldId = masterUserFieldId;
    }

    public String getMasterUserFieldName() {
        return masterUserFieldName;
    }

    public void setMasterUserFieldName(String masterUserFieldName) {
        this.masterUserFieldName = masterUserFieldName;
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
        if (!(o instanceof AppUserFieldMapDTO)) {
            return false;
        }

        return id != null && id.equals(((AppUserFieldMapDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppUserFieldMapDTO{" +
            "id=" + getId() +
            ", appId=" + getAppId() +
            ", groupName='" + getGroupName() + "'" +
            ", userFieldCode='" + getUserFieldCode() + "'" +
            ", userFieldName='" + getUserFieldName() + "'" +
            ", isMandatory='" + getIsMandatory() + "'" +
            ", profileScore=" + getProfileScore() +
            ", validationRegex='" + getValidationRegex() + "'" +
            ", registrationField='" + getRegistrationField() + "'" +
            ", masterUserFieldId=" + getMasterUserFieldId() +
            ", masterUserFieldName='" + getMasterUserFieldName() + "'" +
            "}";
    }
}
