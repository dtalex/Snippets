package org.rao.user.jpa_gui.models;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import org.rao.user.jpa_gui.models.pks.RemapFieldPk;

@IdClass(value=RemapFieldPk.class)
@Entity
@Cacheable
public class RemapField implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2711090849157970209L;
	@Id
	private String remapId;
	@Id
	private String valueToRemap;
	
	private String remappedValue;
	
	
	public RemapField(){}
	public RemapField(String remapId, String valueToRemap, String remappedValue) {
		super();
		this.remapId = remapId;
		this.valueToRemap = valueToRemap;
		this.remappedValue = remappedValue;
	}
	public String getRemapId() {
		return remapId;
	}
	public void setRemapId(String remapId) {
		this.remapId = remapId;
	}
	public String getValueToRemap() {
		return valueToRemap;
	}
	public void setValueToRemap(String valueToRemap) {
		this.valueToRemap = valueToRemap;
	}
	public String getRemappedValue() {
		return remappedValue;
	}
	public void setRemappedValue(String remappedValue) {
		this.remappedValue = remappedValue;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((remapId == null) ? 0 : remapId.hashCode());
		result = prime * result + ((valueToRemap == null) ? 0 : valueToRemap.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RemapField other = (RemapField) obj;
		if (remapId == null) {
			if (other.remapId != null)
				return false;
		} else if (!remapId.equals(other.remapId))
			return false;
		if (valueToRemap == null) {
			if (other.valueToRemap != null)
				return false;
		} else if (!valueToRemap.equals(other.valueToRemap))
			return false;
		return true;
	}
	
}
