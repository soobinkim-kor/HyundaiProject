package monopoly.model;

public class BuildingVO {
	private int locationIdx;
	private int typeIdx;
	private int price;
	
	public BuildingVO() {}
	public BuildingVO(int locationIdx, int typeIdx, int price) {
		this.locationIdx = locationIdx;
		this.typeIdx = typeIdx;
		this.price = price;
	}
	public int getLocationIdx() {
		return locationIdx;
	}
	public void setLocationIdx(int locationIdx) {
		this.locationIdx = locationIdx;
	}
	public int getTypeIdx() {
		return typeIdx;
	}
	public void setTypeIdx(int typeIdx) {
		this.typeIdx = typeIdx;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
