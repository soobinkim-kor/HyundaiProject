package com.kosa;

public class LocationVO {
	private int idx;
	private String city;
	
	public LocationVO() {}
	
	public LocationVO(int idx, String city) {
		this.idx = idx;
		this.city = city;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
