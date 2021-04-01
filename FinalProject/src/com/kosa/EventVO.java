package com.kosa;

public class EventVO {
	private int idx;
	private int locationIdx;
	private String description;
	
	public EventVO() {}
	public EventVO(int idx, int locationIdx, String description) {
		this.idx = idx;
		this.locationIdx = locationIdx;
		this.description = description;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getLocationIdx() {
		return locationIdx;
	}
	public void setLocationIdx(int locationIdx) {
		this.locationIdx = locationIdx;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
