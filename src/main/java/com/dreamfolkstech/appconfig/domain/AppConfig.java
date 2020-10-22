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
 * A AppConfig.
 */
@Entity
@Table(name = "app_config")
public class AppConfig extends AbstractBaseExtensibleEntity<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "code", length = 20, nullable = false)
	private String code;

	@Column(name = "value")
	private String value;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private GenericStatus status;
	
	@Column(name = "device_type")
	private String deviceType;

	public String getName() {
		return name;
	}

	public AppConfig name(String name) {
		this.name = name;
		return this;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public AppConfig code(String code) {
		this.code = code;
		return this;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public AppConfig value(String value) {
		this.value = value;
		return this;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public GenericStatus getStatus() {
		return status;
	}

	public AppConfig status(GenericStatus status) {
		this.status = status;
		return this;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public void setStatus(GenericStatus status) {
		this.status = status;
	}
	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and
	// setters here

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof AppConfig)) {
			return false;
		}
		return this.getId() != null && this.getId().equals(((AppConfig) o).getId());
	}

	@Override
	public int hashCode() {
		return 31;
	}

	// prettier-ignore
	@Override
	public String toString() {
		return "AppConfig{" + "id=" + getId() + ", name='" + getName() + "'" + ", code='" + getCode() + "'"
				+ ", value='" + getValue() + "'" + ", status='" + getStatus() + "'" + "}";
	}
}
