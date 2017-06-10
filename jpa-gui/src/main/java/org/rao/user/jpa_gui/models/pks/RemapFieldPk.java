package org.rao.user.jpa_gui.models.pks;

import java.io.Serializable;

public class RemapFieldPk implements Serializable{

	private String remapId;
	private String valueToRemap;
	
	public RemapFieldPk(){}
	
	public RemapFieldPk(String remapId, String valueToRemap) {
		super();
		this.remapId = remapId;
		this.valueToRemap = valueToRemap;
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
		RemapFieldPk other = (RemapFieldPk) obj;
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
