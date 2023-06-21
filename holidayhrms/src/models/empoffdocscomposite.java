package models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class empoffdocscomposite implements Serializable {

	@Column(name = "eofd_id")
	private int eofrId;

	@Column(name = "eofd_docindex")
	private int eofdDocIndex;

	public int getEofrId() {
		return eofrId;
	}

	public void setEofrId(int eofrId) {
		this.eofrId = eofrId;
	}

	public int getEofdDocIndex() {
		return eofdDocIndex;
	}

	public void setEofdDocIndex(int eofdDocIndex) {
		this.eofdDocIndex = eofdDocIndex;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		empoffdocscomposite that = (empoffdocscomposite) o;
		return eofrId == that.eofrId && eofdDocIndex == that.eofdDocIndex;
	}

	@Override
	public int hashCode() {
		return Objects.hash(eofrId, eofdDocIndex);
	}

	@Override
	public String toString() {
		return "empoffdocscomposite [eofrId=" + eofrId + ", eofdDocIndex=" + eofdDocIndex + "]";
	}

}
