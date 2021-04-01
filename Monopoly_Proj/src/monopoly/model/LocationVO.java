package monopoly.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LocationVO {
	private final IntegerProperty idx;
	private final StringProperty city;
	
	public LocationVO(int idx, String city) {
		this.idx = new SimpleIntegerProperty(idx);
		this.city = new SimpleStringProperty(city);
	}

	public int getIdx() {
		return idx.get();
	}

	public void setIdx(int idx) {
		this.idx.set(idx);
	}
	
	public IntegerProperty idxProperty() {
		return idx;
	}

	public String getCity() {
		return city.get();
	}

	public void setCity(String city) {
		this.city.set(city);
	}
	
	public StringProperty cityProperty() {
		return city;
	}
}
