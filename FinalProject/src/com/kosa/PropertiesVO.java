package com.kosa;

public class PropertiesVO {
	private int usersIdx;
	private int buildingLocationIdx;
	private int buildingTypeIdx;
	private int fine;
	
	public PropertiesVO() {}
	public PropertiesVO(int usersIdx, int buildingLocationIdx, int buildingTypeIdx, int fine) {
		this.usersIdx = usersIdx;
		this.buildingLocationIdx = buildingLocationIdx;
		this.buildingTypeIdx = buildingTypeIdx;
		this.fine = fine;
	}
	public int getUsersIdx() {
		return usersIdx;
	}
	public void setUsersIdx(int usersIdx) {
		this.usersIdx = usersIdx;
	}
	public int getBuildingLocationIdx() {
		return buildingLocationIdx;
	}
	public void setBuildingLocationIdx(int buildingLocationIdx) {
		this.buildingLocationIdx = buildingLocationIdx;
	}
	public int getBuildingTypeIdx() {
		return buildingTypeIdx;
	}
	public void setBuildingTypeIdx(int buildingTypeIdx) {
		this.buildingTypeIdx = buildingTypeIdx;
	}
	public int getFine() {
		return fine;
	}
	public void setFine(int fine) {
		this.fine = fine;
	}

}
